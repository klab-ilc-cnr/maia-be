package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.WorkspaceDto;
import it.cnr.ilc.projectx.mediator.Request;

public class SaveWorkspaceRequest implements Request<Void> {

    private final WorkspaceDto workspace;

    public SaveWorkspaceRequest(final WorkspaceDto workspace) {
        this.workspace = workspace;
    }

    public WorkspaceDto getWorkspace() {
        return workspace;
    }
}
