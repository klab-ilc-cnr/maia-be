package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.RequestHandler2;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.request.CreateUser;
import it.cnr.ilc.projectx.service.KeycloakAdminService;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateUserHandler implements RequestHandler2<CreateUser, UserDto> {
    @NonNull
    private final UserService userService;

    @NonNull
    private final KeycloakAdminService keycloakAdminService;

    @Override
    public XResult<UserDto> handle(CreateUser request) {
        try {
            KeycloakAdminService.KeycloakAdminClient keycloakAdminClient = keycloakAdminService.getClient();
            User userEntity = userService.mapToEntity(request.getUser());
            keycloakAdminClient.createUser(userEntity, "test");

            UserDto responseUserDto = userService.save(request.getUser());
            return new XResult<>(responseUserDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
