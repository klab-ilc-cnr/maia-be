package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.WorkspaceChoiceDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.UpdateWorkspaceChoiceRequest;
import it.cnr.ilc.projectx.service.WorkspaceService;
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
public class UpdateWorkspaceChoiceHandler implements RequestHandler<UpdateWorkspaceChoiceRequest, WorkspaceChoiceDto> {
    @NonNull
    private final WorkspaceService workspaceService;

    @Override
    public WorkspaceChoiceDto handle(UpdateWorkspaceChoiceRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<WorkspaceChoiceDto> handleXResult(UpdateWorkspaceChoiceRequest request) {
        try {
            WorkspaceChoiceDto workspaceChoiceDto = workspaceService.update(request.getUpdateWorkspaceDto());

            return new XResult(workspaceChoiceDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
