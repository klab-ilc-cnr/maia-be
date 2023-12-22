package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.mediator.Request;

public class DeleteAnnotationRelationRequest implements Request<Boolean> {

    private final Long relationId;

    public DeleteAnnotationRelationRequest(final Long relationId) {
        this.relationId = relationId;
    }

    public Long getRelationId() {
        return this.relationId;
    }
}
