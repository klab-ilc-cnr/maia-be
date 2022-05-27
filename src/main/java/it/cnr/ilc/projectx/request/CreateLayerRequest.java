package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.CreateWorkspaceDto;
import it.cnr.ilc.projectx.dto.LayerDto;
import it.cnr.ilc.projectx.mediator.Request;

public class CreateLayerRequest implements Request<LayerDto> {
    private final LayerDto layerDto;

    public CreateLayerRequest(final LayerDto layerDto) {
        this.layerDto = layerDto;
    }

    public LayerDto getLayerDto() {
        return this.layerDto;
    }
}
