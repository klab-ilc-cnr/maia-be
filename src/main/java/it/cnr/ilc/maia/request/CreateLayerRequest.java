package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.LayerDto;
import it.cnr.ilc.maia.mediator.Request;

public class CreateLayerRequest implements Request<LayerDto> {

    private final LayerDto layerDto;

    public CreateLayerRequest(final LayerDto layerDto) {
        this.layerDto = layerDto;
    }

    public LayerDto getLayerDto() {
        return this.layerDto;
    }
}
