package it.cnr.ilc.projectx.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.request.CreateUserRequest;
import it.cnr.ilc.projectx.service.KeycloakAdminService;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.spi.NotImplementedYetException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateUserHandler implements RequestHandler<CreateUserRequest, CreateUserDto> {
    @NonNull
    private final UserService userService;

    @NonNull
    private final KeycloakAdminService keycloakAdminService;

    @Override
    public CreateUserDto handle(CreateUserRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<CreateUserDto> handleXResult(CreateUserRequest request) throws JsonProcessingException {
        try {
            CreateUserDto responseUserDto = userService.add(request.getUser());

            List selectedLanguagesForUser = request.getUser().getLanguages();

            KeycloakAdminService.KeycloakAdminClient keycloakAdminClient = keycloakAdminService.getClient();
            User userEntity = userService.mapToEntity(request.getUser());
            keycloakAdminClient.createUser(userEntity, null, selectedLanguagesForUser);

            return new XResult<>(responseUserDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
