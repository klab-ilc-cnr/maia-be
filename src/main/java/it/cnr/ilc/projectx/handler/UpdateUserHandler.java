package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.dto.UserUpdatedDto;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public XResult<UserDto> handleXResult(UpdateUser request) {
        try {

            UserDto userDto = userService.getUserByEmail(request.getUser().getEmail());

            if (userDto == null) {
                log.error("Cannot find user for email " + request.getUser().getEmail());
                throw new NotFoundException("Cannot find user for email " + request.getUser().getEmail());
            }

            updateDtoUser(userDto, request.getUser());

            UserUpdatedDto userUpdatedDto = userService.update(userDto);

            User userEntity = userService.mapToEntity(request.getUser());
            KeycloakAdminService.KeycloakAdminClient keycloakClient = keycloakAdminService.getClient();
            String userId = keycloakClient.getUserByEmail(userDto.getEmail()).getId();
            keycloakClient.updateUser(userEntity, userId);

            return new XResult(userUpdatedDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private void updateDtoUser(UserDto userTobeUpdated, UserDto user) {
        UserUpdatedDto updatedUser = new UserUpdatedDto();
        userTobeUpdated.setRole(user.getRole());
        userTobeUpdated.setActive(user.isActive());
        userTobeUpdated.setName(user.getName());
        userTobeUpdated.setSurname(user.getSurname());
    }
}
