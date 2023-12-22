package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.LayerChoiceDto;
import it.cnr.ilc.maia.dto.UpdateLayerChoiceDto;
import it.cnr.ilc.maia.mediator.Request;

public class UpdateLayerChoiceRequest implements Request<LayerChoiceDto> {

    private final UpdateLayerChoiceDto updateLayerChoiceDto;

    public UpdateLayerChoiceRequest(final UpdateLayerChoiceDto layerChoiceDto) {
        this.updateLayerChoiceDto = layerChoiceDto;
    }

    public UpdateLayerChoiceDto getUpdateLayerChoiceDto() {
        return this.updateLayerChoiceDto;
    }
}
