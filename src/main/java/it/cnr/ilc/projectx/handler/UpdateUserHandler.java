package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.request.UpdateUserRequest;
import it.cnr.ilc.projectx.service.KeycloakAdminService;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.spi.NotImplementedYetException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserHandler implements RequestHandler<UpdateUserRequest, UserDto> {
    @NonNull
    private final UserService userService;

    @NonNull
    private final KeycloakAdminService keycloakAdminService;

    @Override
    public UserDto handle(UpdateUserRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<UserDto> handleXResult(UpdateUserRequest request) {
        try {

            UserDto userDto = userService.update(request.getUser());

            User userEntity = userService.mapToEntity(userDto);
            KeycloakAdminService.KeycloakAdminClient keycloakClient = keycloakAdminService.getClient();
            String userId = keycloakClient.getUserByEmail(userDto.getEmail()).getId();
            keycloakClient.updateUser(userEntity, userId);

            return new XResult(userDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
