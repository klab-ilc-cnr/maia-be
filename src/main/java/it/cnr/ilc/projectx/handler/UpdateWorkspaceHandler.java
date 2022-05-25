package it.cnr.ilc.projectx.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.dto.WorkspaceChoiceDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.request.UpdateUserRequest;
import it.cnr.ilc.projectx.request.UpdateWorkspaceRequest;
import it.cnr.ilc.projectx.service.KeycloakAdminService;
import it.cnr.ilc.projectx.service.WorkspaceService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.spi.NotImplementedYetException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateWorkspaceHandler implements RequestHandler<UpdateWorkspaceRequest, WorkspaceChoiceDto> {
    @NonNull
    private final WorkspaceService workspaceService;

    @Override
    public WorkspaceChoiceDto handle(UpdateWorkspaceRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<WorkspaceChoiceDto> handleXResult(UpdateWorkspaceRequest request) {
        try {
            WorkspaceChoiceDto workspaceChoiceDto = workspaceService.update(request.getUpdateWorkspaceDto());

            return new XResult(workspaceChoiceDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
