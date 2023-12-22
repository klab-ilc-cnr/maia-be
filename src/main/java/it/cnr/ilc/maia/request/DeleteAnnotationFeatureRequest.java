package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.mediator.Request;

public class DeleteAnnotationFeatureRequest implements Request<Boolean> {

    private final Long annotationId;

    public DeleteAnnotationFeatureRequest(final Long annotationId) {
        this.annotationId = annotationId;
    }

    public Long getAnnotationId() {
        return this.annotationId;
    }
}
