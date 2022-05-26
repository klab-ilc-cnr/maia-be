package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.TileDto;
import it.cnr.ilc.projectx.mediator.Request;

import java.util.List;

public class SaveTilesRequest implements Request<Void> {
    private final Long workspaceId;
    private final List<TileDto> tiles;

    public SaveTilesRequest(final Long workspaceId, final List<TileDto> tiles) {
        this.workspaceId = workspaceId;
        this.tiles = tiles;
    }

    public List<TileDto> getTiles() {
        return this.tiles;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }
}
