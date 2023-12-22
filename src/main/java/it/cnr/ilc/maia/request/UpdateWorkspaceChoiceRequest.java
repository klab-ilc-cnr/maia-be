package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.UpdateWorkspaceChoiceDto;
import it.cnr.ilc.maia.dto.WorkspaceChoiceDto;
import it.cnr.ilc.maia.mediator.Request;

public class UpdateWorkspaceChoiceRequest implements Request<WorkspaceChoiceDto> {

    private final UpdateWorkspaceChoiceDto updateWorkspaceChoiceDto;

    public UpdateWorkspaceChoiceRequest(final UpdateWorkspaceChoiceDto updateWorkspaceChoiceDto) {
        this.updateWorkspaceChoiceDto = updateWorkspaceChoiceDto;
    }

    public UpdateWorkspaceChoiceDto getUpdateWorkspaceDto() {
        return this.updateWorkspaceChoiceDto;
    }
}
