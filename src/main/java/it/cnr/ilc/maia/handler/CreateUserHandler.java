package it.cnr.ilc.maia.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.ilc.maia.dto.CreateUserDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.CreateUserRequest;
import it.cnr.ilc.maia.service.UserService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateUserHandler implements RequestHandler<CreateUserRequest, CreateUserDto> {

    @NonNull
    private final UserService userService;

    @Override
    public CreateUserDto handle(CreateUserRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<CreateUserDto> handleXResult(CreateUserRequest request) throws JsonProcessingException {
        CreateUserDto responseUserDto = userService.add(request.getUser());
        return new XResult<>(responseUserDto);
    }
}
