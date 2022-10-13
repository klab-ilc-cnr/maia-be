package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.mediator.Request;

public class DeleteLayerRequest implements Request<Boolean> {
    private final Long layerId;

    public DeleteLayerRequest(final Long layerId) {
        this.layerId = layerId;
    }

    public Long getLayerId() {
        return this.layerId;
    }
}
