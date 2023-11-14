package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.WorkspaceChoiceDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.UpdateWorkspaceChoiceRequest;
import it.cnr.ilc.projectx.service.WorkspaceService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateWorkspaceChoiceHandler implements RequestHandler<UpdateWorkspaceChoiceRequest, WorkspaceChoiceDto> {

    @NonNull
    private final WorkspaceService workspaceService;

    @Override
    public WorkspaceChoiceDto handle(UpdateWorkspaceChoiceRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<WorkspaceChoiceDto> handleXResult(UpdateWorkspaceChoiceRequest request) {
        WorkspaceChoiceDto workspaceChoiceDto = workspaceService.update(request.getUpdateWorkspaceDto());
        return new XResult(workspaceChoiceDto);
    }
}
