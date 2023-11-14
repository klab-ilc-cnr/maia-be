package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateFeatureRequest implements Request<FeatureDto> {

    private final FeatureDto featureDto;

    public UpdateFeatureRequest(final FeatureDto featureDto) {
        this.featureDto = featureDto;
    }

    public FeatureDto getFeatureDto() {
        return this.featureDto;
    }
}
