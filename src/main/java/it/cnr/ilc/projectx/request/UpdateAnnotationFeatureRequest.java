package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.AnnotationFeatureDto;
import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateAnnotationFeatureRequest implements Request<AnnotationFeatureDto> {
    private final AnnotationFeatureDto annotationFeatureDto;

    public UpdateAnnotationFeatureRequest(final AnnotationFeatureDto annotationFeatureDto) {
        this.annotationFeatureDto = annotationFeatureDto;
    }

    public AnnotationFeatureDto getAnnotationFeatureDto() {
        return this.annotationFeatureDto;
    }
}
