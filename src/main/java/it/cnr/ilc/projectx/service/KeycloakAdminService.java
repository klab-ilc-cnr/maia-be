package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.config.ApplicationConfig;
import it.cnr.ilc.projectx.config.KeycloakConfig;
import it.cnr.ilc.projectx.exception.keycloak.*;
import it.cnr.ilc.projectx.model.Role;
import it.cnr.ilc.projectx.model.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakAdminService {

    @NonNull
    private final ApplicationConfig applicationConfig;

    @NonNull
    private final KeycloakConfig keycloakConfig;

    public KeycloakAdminClient getClient() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakConfig.getAuthServerUrl())
                .realm(keycloakConfig.getRealmName())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(applicationConfig.getKeycloak().getUserManager().getClientId())
                .clientSecret(applicationConfig.getKeycloak().getUserManager().getClientSecret())
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(1)
                                .build()
                )
                .build();

        keycloak.tokenManager().getAccessToken();

        RealmResource realm = keycloak.realm(keycloakConfig.getRealmName());
        String defaultRolesName = applicationConfig.getKeycloak().getUserManager().getDefaultRoleName();

        return new KeycloakAdminClient(realm, defaultRolesName);
    }

    public static class KeycloakAdminClient {

        private final RealmResource realm;
        private final String defaultRolesName;

        private KeycloakAdminClient(RealmResource realm, String defaultRolesName) {
            this.realm = realm;
            this.defaultRolesName = defaultRolesName;
        }

        public String createUser(User user, String password) {
            UsersResource users = realm.users();

            String email = user.getEmail();
            String firstName = user.getName();
            String lastName = user.getSurname();
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(email);
            userRepresentation.setEmail(email);
            userRepresentation.setFirstName(firstName);
            userRepresentation.setLastName(lastName);
            userRepresentation.setEmailVerified(true);

//            addAttributesOnUser(userRepresentation);

            //questo campo deve essere sempre a true sennò le mail da keycloak non vengono inviate
            userRepresentation.setEnabled(user.isActive());
            if (Objects.nonNull(password)) {
                CredentialRepresentation credential = new CredentialRepresentation();
                credential.setType(CredentialRepresentation.PASSWORD);
                credential.setTemporary(false);
                credential.setValue(password.trim());
                userRepresentation.setCredentials(Arrays.asList(credential));
            } else {
                userRepresentation.setRequiredActions(Collections.singletonList(UserAction.UPDATE_PASSWORD.getCode()));
            }

            Response response = null;
            try {
                try {
                    response = users.create(userRepresentation);
                } catch (Exception e) {
                    throw new KeycloakConnectionException(String.format("Cannot create user <%s>", email), e);
                }

                int statusCode = response.getStatusInfo().getStatusCode();
                if (statusCode == HttpStatus.CONFLICT.value()) {
                    throw new KeycloakEntityAlreadyExistsException(String.format("User <%s> already exists", email));
                }

                if (statusCode != HttpStatus.CREATED.value()) {
                    throwError(response);
                }

                String userId = CreatedResponseUtil.getCreatedId(response);
                assignRoles(userId, user.getRoles());

                return getCreatedEntityId(response.getHeaderString("Location"));
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        }

        //FIXME impostare le lingue qui prese dall'api del cnr
        private void addAttributesOnUser(UserRepresentation userRepresentation) {
            JSONArray jsonArray = new JSONArray();

            jsonArray.put("it");
            jsonArray.put("de");
            jsonArray.put("fr");

            //            userRepresentation.singleAttribute("locale", user.getUserLang().name().toLowerCase());
            userRepresentation.singleAttribute("lang", jsonArray.toString());
//            userRepresentation.setAttributes(Collections.singletonMap("lang", Arrays.asList("it","de","fr")));
        }

        public void updateUser(User user, String id) {
            UsersResource users = realm.users();

            String email = user.getEmail();
            String firstName = user.getName();
            String lastName = user.getSurname();
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(email);
            userRepresentation.setEmail(email);
            userRepresentation.setFirstName(firstName);
            userRepresentation.setLastName(lastName);
            userRepresentation.setEmailVerified(true);
            //questo campo deve essere sempre a true sennò le mail da keycloak non vengono inviate
            userRepresentation.setEnabled(user.isActive());

            assignRoles(id, user.getRoles());

            users.get(id).update(userRepresentation);
        }

        public UserRepresentation getUserByEmail(String email) {
            UsersResource users = realm.users();

            List<UserRepresentation> rs = users.search(email);
            if (rs.isEmpty()) {
                throw new KeycloakEntityNotFoundException(String.format("Cannot find user <%s>", email));
            }
            if (rs.size() > 1) {
                throw new KeycloakTooManyEntitiesException(String.format("Found too many users with email <%s>: %d", email, rs.size()));
            }

            return rs.get(0);
        }

        public void sendVerificationEmail(String userId) {
            try {
                UsersResource users = realm.users();
                UserResource user = users.get(userId);
                user.sendVerifyEmail();
            } catch (Exception e) {
                throw new KeycloakRequestException(String.format("Sending verification email to user %s: %s", userId, e.getMessage()), e);
            }
        }

        public void demandPasswordChange(String email) {
            UserRepresentation user = this.getUserByEmail(email);
            UserResource userResource = realm.users().get(user.getId());
            userResource.executeActionsEmail(Collections.singletonList(
                    UserAction.UPDATE_PASSWORD.getCode()
            ));
        }

        private String getCreatedEntityId(String location) {
            String[] paths = location.split("/");
            return paths[paths.length - 1];
        }

        private void throwError(Response response) {
            int statusCode = response.getStatusInfo().getStatusCode();

            String error = response.readEntity(String.class);
            if (statusCode >= 500) {
                throw new KeycloakServerException(error);
            }

            throw new KeycloakRequestException(error);
        }

        private void assignRoles(String userId, Set<Role> roles) {
            List<String> originalRoleList = getServerExistingRoles(realm.users().get(userId).roles().realmLevel().listAll());

            List<String> roleToBeList = roles
                    .stream()
                    .map(Role::name)
                    .collect(Collectors.toList());

            //role to remove: original - new
            List<RoleRepresentation> rolesToRemove = getDifferenceBetweenFirstListAndSecond(originalRoleList, roleToBeList);
            if (!rolesToRemove.isEmpty())
                realm.users()
                        .get(userId)
                        .roles()
                        .realmLevel()
                        .remove(rolesToRemove);

            //role to add: new - original
            List<RoleRepresentation> rolesToAdd = getDifferenceBetweenFirstListAndSecond(roleToBeList, originalRoleList);
            if (!rolesToAdd.isEmpty())
                realm.users()
                        .get(userId)
                        .roles()
                        .realmLevel()
                        .add(rolesToAdd);
        }

        private List<RoleRepresentation> getDifferenceBetweenFirstListAndSecond(List<String> first, List<String> second) {
            //si ignorano i ruoli di default (ad uso keycloak)
            List<RoleRepresentation> existingRoles = realm.roles().list().stream()
                    .filter(roleRepresentation -> !roleRepresentation.getName().equals(defaultRolesName))
                    .collect(Collectors.toList());
            List<String> serverRoleList = getServerExistingRoles(existingRoles);

            List<String> difference = new ArrayList<>(first);
            difference.removeAll(second);

            List<RoleRepresentation> addingRoles = new ArrayList<>();
            for (String role : difference) {
                int index = serverRoleList.indexOf(role);
                if (index != -1) {
                    addingRoles.add(existingRoles.get(index));
                } else {
                    log.info("Role " + role + " doesn't exist");
                }
            }
            return addingRoles;
        }

        private List<String> getServerExistingRoles(List<RoleRepresentation> existingRoles) {
            //si ignorano uma e offline (ad uso keycloak)
            return existingRoles
                    .stream()
                    .map(RoleRepresentation::getName).filter(s ->
                            !s.equals(defaultRolesName)
                    )
                    .collect(Collectors.toList());
        }

    }

    @Getter
    private enum UserAction {
        UPDATE_PASSWORD("UPDATE_PASSWORD");

        private final String code;

        UserAction(String code) {
            this.code = code;
        }
    }
}
