package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.CreateWorkspaceDto;
import it.cnr.ilc.maia.dto.WorkspaceChoiceDto;
import it.cnr.ilc.maia.mediator.Request;

public class CreateWorkspaceRequest implements Request<WorkspaceChoiceDto> {

    private final CreateWorkspaceDto createWorkspaceDto;

    public CreateWorkspaceRequest(final CreateWorkspaceDto createWorkspaceDto) {
        this.createWorkspaceDto = createWorkspaceDto;
    }

    public CreateWorkspaceDto getCreateWorkspaceDto() {
        return this.createWorkspaceDto;
    }
}
