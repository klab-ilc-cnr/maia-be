package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.WorkspaceChoiceDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.CreateWorkspaceRequest;
import it.cnr.ilc.projectx.service.KeycloakAdminService;
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
public class CreateWorkspaceHandler implements RequestHandler<CreateWorkspaceRequest, WorkspaceChoiceDto> {
    @NonNull
    private final WorkspaceService workspaceService;

    @NonNull
    private final KeycloakAdminService keycloakAdminService;

    @Override
    public WorkspaceChoiceDto handle(CreateWorkspaceRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<WorkspaceChoiceDto> handleXResult(CreateWorkspaceRequest request) {
        try {
            WorkspaceChoiceDto responseWorkspaceChoiceDto = workspaceService.add(request.getCreateWorkspaceDto());

            return new XResult<>(responseWorkspaceChoiceDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
