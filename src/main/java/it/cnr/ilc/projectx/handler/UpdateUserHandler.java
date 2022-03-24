package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.request.CreateUser;
import it.cnr.ilc.projectx.request.UpdateUser;
import it.cnr.ilc.projectx.service.KeycloakAdminService;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.jboss.resteasy.spi.NotImplementedYetException;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserHandler implements RequestHandler<UpdateUser, UserDto> {
    @NonNull
    private final UserService userService;

    @NonNull
    private final KeycloakAdminService keycloakAdminService;

    @Override
    public UserDto handle(UpdateUser request) {
        throw new NotImplementedYetException();
    }

    @Override
    public XResult<UserDto> handleXResult(UpdateUser request) {
        try {

            UserDto userTobeUpdated = userService.getUserByEmail(request.getUser().getEmail());

            if (userTobeUpdated == null) {
                log.error("Cannot find user for email " + request.getUser().getEmail());
                throw new NotFoundException("Cannot find user for email " + request.getUser().getEmail());
            }

            updateDtoUser(userTobeUpdated, request.getUser());

            UserDto responseUserDto = userService.update(userTobeUpdated);

            User userEntity = userService.mapToEntity(request.getUser());
            KeycloakAdminService.KeycloakAdminClient keycloakClient = keycloakAdminService.getClient();
            String userId = keycloakClient.getUserByEmail(request.getUser().getEmail()).getId();
            keycloakClient.updateUser(userEntity, userId);

            return new XResult(responseUserDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private void updateDtoUser(UserDto userTobeUpdated, UserDto user) {
        userTobeUpdated.setRole(user.getRole());
        userTobeUpdated.setActive(user.isActive());
        userTobeUpdated.setName(user.getName());
        userTobeUpdated.setSurname(user.getSurname());
    }
}
