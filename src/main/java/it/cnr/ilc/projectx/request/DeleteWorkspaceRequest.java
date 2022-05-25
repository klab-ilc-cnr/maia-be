package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.CreateWorkspaceDto;
import it.cnr.ilc.projectx.mediator.Request;

public class DeleteWorkspaceRequest implements Request<Long> {
    private final Long workspaceId;

    public DeleteWorkspaceRequest(final Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Long getWorkspaceId() {
        return this.workspaceId;
    }
}
