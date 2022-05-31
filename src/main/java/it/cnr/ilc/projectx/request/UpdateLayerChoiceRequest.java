package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.LayerChoiceDto;
import it.cnr.ilc.projectx.dto.UpdateLayerChoiceDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateLayerChoiceRequest implements Request<LayerChoiceDto> {
    private final UpdateLayerChoiceDto updateLayerChoiceDto;

    public UpdateLayerChoiceRequest(final UpdateLayerChoiceDto layerChoiceDto) {
        this.updateLayerChoiceDto = layerChoiceDto;
    }

    public UpdateLayerChoiceDto getUpdateLayerChoiceDto() {
        return this.updateLayerChoiceDto;
    }
}
