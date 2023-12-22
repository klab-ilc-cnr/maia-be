package it.cnr.ilc.maia.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.ilc.maia.dto.UserDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.UpdateUserRequest;
import it.cnr.ilc.maia.service.UserService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateUserHandler implements RequestHandler<UpdateUserRequest, UserDto> {

    @NonNull
    private final UserService userService;

    @Override
    public UserDto handle(UpdateUserRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<UserDto> handleXResult(UpdateUserRequest request) throws JsonProcessingException {
        UserDto userDto = userService.update(request.getUser());
        return new XResult(userDto);
    }
}
