package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.AnnotationFeatureDto;
import it.cnr.ilc.projectx.mediator.Request;

public class CreateAnnotationFeatureRequest implements Request<AnnotationFeatureDto> {

    private final AnnotationFeatureDto createFeatureDto;

    public CreateAnnotationFeatureRequest(final AnnotationFeatureDto annotationFeatureDto) {
        this.createFeatureDto = annotationFeatureDto;
    }

    public AnnotationFeatureDto getAnnotationFeatureDto() {
        return this.createFeatureDto;
    }
}
