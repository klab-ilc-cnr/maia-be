package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.mediator.Request;

public class DeleteWorkspaceRequest implements Request<Long> {

    private final Long workspaceId;

    public DeleteWorkspaceRequest(final Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Long getWorkspaceId() {
        return this.workspaceId;
    }
}
