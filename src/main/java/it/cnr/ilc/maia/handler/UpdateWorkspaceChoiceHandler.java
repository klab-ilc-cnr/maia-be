package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.WorkspaceChoiceDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.UpdateWorkspaceChoiceRequest;
import it.cnr.ilc.maia.service.WorkspaceService;
import it.cnr.ilc.maia.xresults.XResult;
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
