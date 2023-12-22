package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.CreateFeatureDto;
import it.cnr.ilc.maia.mediator.Request;

public class CreateFeatureRequest implements Request<CreateFeatureDto> {

    private final CreateFeatureDto createFeatureDto;

    public CreateFeatureRequest(final CreateFeatureDto createFeatureDto) {
        this.createFeatureDto = createFeatureDto;
    }

    public CreateFeatureDto getFeatureDto() {
        return this.createFeatureDto;
    }
}
