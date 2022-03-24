package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.request.CreateUser;
import it.cnr.ilc.projectx.service.KeycloakAdminService;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.spi.NotImplementedYetException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateUserHandler implements RequestHandler<CreateUser, CreateUserDto> {
    @NonNull
    private final UserService userService;

    @NonNull
    private final KeycloakAdminService keycloakAdminService;

    @Override
    public CreateUserDto handle(CreateUser request) {
        throw new NotImplementedYetException();
    }

    @Override
    public XResult<CreateUserDto> handleXResult(CreateUser request) {
        try {
            KeycloakAdminService.KeycloakAdminClient keycloakAdminClient = keycloakAdminService.getClient();
            User userEntity = userService.mapToEntity(request.getUser());
            keycloakAdminClient.createUser(userEntity, "test");

            CreateUserDto responseUserDto = userService.add(request.getUser());
            return new XResult<>(responseUserDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
