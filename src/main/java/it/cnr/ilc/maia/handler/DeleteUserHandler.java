package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.model.User;
import it.cnr.ilc.maia.request.DeleteUserRequest;
import it.cnr.ilc.maia.service.UserService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteUserHandler implements RequestHandler<DeleteUserRequest, Long> {

    @NonNull
    private final UserService userService;

    @Override
    public Long handle(DeleteUserRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<Long> handleXResult(DeleteUserRequest request) {
        User user = userService.retrieveUser(request.getUserId());
        userService.delete(user);
        return new XResult<>(request.getUserId());
    }
}
