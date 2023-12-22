package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.AnnotationFeatureDto;
import it.cnr.ilc.maia.mediator.Request;

public class UpdateAnnotationFeatureRequest implements Request<AnnotationFeatureDto> {

    private final AnnotationFeatureDto annotationFeatureDto;

    public UpdateAnnotationFeatureRequest(final AnnotationFeatureDto annotationFeatureDto) {
        this.annotationFeatureDto = annotationFeatureDto;
    }

    public AnnotationFeatureDto getAnnotationFeatureDto() {
        return this.annotationFeatureDto;
    }
}
