package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.request.CreateUser;
import it.cnr.ilc.projectx.service.KeycloakAdminService;
import it.cnr.ilc.projectx.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserHandler implements RequestHandler<CreateUser, UserDto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisplayConsoleMessageRequestHandler.class);
    @NonNull
    private final UserService userService;

    @NonNull
    private final KeycloakAdminService keycloakAdminService;

    @Override
    public UserDto handle(CreateUser request) {
        try {
            KeycloakAdminService.KeycloakAdminClient keycloakAdminClient = keycloakAdminService.getClient();
            User userEntity = userService.mapToEntity(request.getUser());
            keycloakAdminClient.createUser(userEntity, "test");

            return userService.save(request.getUser());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
