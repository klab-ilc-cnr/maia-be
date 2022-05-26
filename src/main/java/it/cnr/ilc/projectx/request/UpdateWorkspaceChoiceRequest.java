package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.UpdateWorkspaceDto;
import it.cnr.ilc.projectx.dto.WorkspaceChoiceDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateWorkspaceChoiceRequest implements Request<WorkspaceChoiceDto> {
    private final UpdateWorkspaceDto updateWorkspaceDto;

    public UpdateWorkspaceChoiceRequest(final UpdateWorkspaceDto updateWorkspaceDto) {
        this.updateWorkspaceDto = updateWorkspaceDto;
    }

    public UpdateWorkspaceDto getUpdateWorkspaceDto() {
        return this.updateWorkspaceDto;
    }
}
