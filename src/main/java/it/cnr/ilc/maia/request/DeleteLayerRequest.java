package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.mediator.Request;

public class DeleteLayerRequest implements Request<Boolean> {

    private final Long layerId;

    public DeleteLayerRequest(final Long layerId) {
        this.layerId = layerId;
    }

    public Long getLayerId() {
        return this.layerId;
    }
}
