package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.UpdateWorkspaceChoiceDto;
import it.cnr.ilc.projectx.dto.WorkspaceChoiceDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateWorkspaceChoiceRequest implements Request<WorkspaceChoiceDto> {

    private final UpdateWorkspaceChoiceDto updateWorkspaceChoiceDto;

    public UpdateWorkspaceChoiceRequest(final UpdateWorkspaceChoiceDto updateWorkspaceChoiceDto) {
        this.updateWorkspaceChoiceDto = updateWorkspaceChoiceDto;
    }

    public UpdateWorkspaceChoiceDto getUpdateWorkspaceDto() {
        return this.updateWorkspaceChoiceDto;
    }
}
