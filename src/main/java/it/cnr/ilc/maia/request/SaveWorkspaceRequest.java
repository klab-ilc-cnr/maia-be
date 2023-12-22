package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.WorkspaceDto;
import it.cnr.ilc.maia.mediator.Request;

public class SaveWorkspaceRequest implements Request<Void> {

    private final WorkspaceDto workspace;

    public SaveWorkspaceRequest(final WorkspaceDto workspace) {
        this.workspace = workspace;
    }

    public WorkspaceDto getWorkspace() {
        return workspace;
    }
}
