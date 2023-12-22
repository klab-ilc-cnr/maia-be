package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.AnnotationFeatureDto;
import it.cnr.ilc.maia.mediator.Request;

public class CreateAnnotationFeatureRequest implements Request<AnnotationFeatureDto> {

    private final AnnotationFeatureDto createFeatureDto;

    public CreateAnnotationFeatureRequest(final AnnotationFeatureDto annotationFeatureDto) {
        this.createFeatureDto = annotationFeatureDto;
    }

    public AnnotationFeatureDto getAnnotationFeatureDto() {
        return this.createFeatureDto;
    }
}
