package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.CreateFeatureDto;
import it.cnr.ilc.projectx.mediator.Request;

public class CreateFeatureRequest implements Request<CreateFeatureDto> {

    private final CreateFeatureDto createFeatureDto;

    public CreateFeatureRequest(final CreateFeatureDto createFeatureDto) {
        this.createFeatureDto = createFeatureDto;
    }

    public CreateFeatureDto getFeatureDto() {
        return this.createFeatureDto;
    }
}
