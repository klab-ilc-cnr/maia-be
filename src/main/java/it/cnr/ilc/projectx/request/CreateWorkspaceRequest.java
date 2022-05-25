package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.CreateWorkspaceDto;
import it.cnr.ilc.projectx.dto.WorkspaceChoiceDto;
import it.cnr.ilc.projectx.mediator.Request;

public class CreateWorkspaceRequest implements Request<WorkspaceChoiceDto> {
    private final CreateWorkspaceDto createWorkspaceDto;

    public CreateWorkspaceRequest(final CreateWorkspaceDto createWorkspaceDto) {
        this.createWorkspaceDto = createWorkspaceDto;
    }

    public CreateWorkspaceDto getCreateWorkspaceDto() {
        return this.createWorkspaceDto;
    }
}
