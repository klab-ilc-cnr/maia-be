package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateAnnotationRelationRequest implements Request<AnnotationRelationDto> {
    private final AnnotationRelationDto annotationRelationDto;

    public UpdateAnnotationRelationRequest(final AnnotationRelationDto annotationRelationDto) {
        this.annotationRelationDto = annotationRelationDto;
    }

    public AnnotationRelationDto getAnnotationRelationDto() {
        return this.annotationRelationDto;
    }
}
