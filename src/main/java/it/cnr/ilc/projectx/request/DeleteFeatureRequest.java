package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.mediator.Request;

public class DeleteFeatureRequest implements Request<Boolean> {

    private final Long layerId;
    private final Long featureId;

    public DeleteFeatureRequest(final Long layerId, final Long featureId) {
        this.layerId = layerId;
        this.featureId = featureId;
    }

    public Long getLayerId() {
        return this.layerId;
    }

    public Long getFeatureId() {
        return this.featureId;
    }
}
