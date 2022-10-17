package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.mediator.Request;

public class DeleteAnnotationFeatureRequest implements Request<Boolean> {

    private final Long annotationId;

    public DeleteAnnotationFeatureRequest(final Long annotationId) {
        this.annotationId = annotationId;
    }

    public Long getAnnotationId() {
        return this.annotationId;
    }
}
