package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.FeatureDto;
import it.cnr.ilc.maia.mediator.Request;

public class UpdateFeatureRequest implements Request<FeatureDto> {

    private final FeatureDto featureDto;

    public UpdateFeatureRequest(final FeatureDto featureDto) {
        this.featureDto = featureDto;
    }

    public FeatureDto getFeatureDto() {
        return this.featureDto;
    }
}
