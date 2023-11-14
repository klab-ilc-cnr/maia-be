package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.Workspace;
import it.cnr.ilc.projectx.request.DeleteWorkspaceRequest;
import it.cnr.ilc.projectx.service.WorkspaceService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteWorkspaceHandler implements RequestHandler<DeleteWorkspaceRequest, Long> {

    @NonNull
    private final WorkspaceService workspaceService;

    @Override
    public Long handle(DeleteWorkspaceRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<Long> handleXResult(DeleteWorkspaceRequest request) {
        Workspace workspace = workspaceService.retrieveWorkspace(request.getWorkspaceId());
        workspaceService.delete(workspace);
        return new XResult<>(request.getWorkspaceId());
    }
}
