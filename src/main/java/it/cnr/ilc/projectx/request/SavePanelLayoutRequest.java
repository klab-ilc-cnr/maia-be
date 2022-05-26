package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.mediator.Request;

public class SavePanelLayoutRequest implements Request<Void> {
    private final Long workspaceId;
    private final String layout;

    public SavePanelLayoutRequest(final Long workspaceId, final String layout) {
        this.workspaceId = workspaceId;
        this.layout = layout;
    }

    public String getLayout() {
        return this.layout;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }
}
