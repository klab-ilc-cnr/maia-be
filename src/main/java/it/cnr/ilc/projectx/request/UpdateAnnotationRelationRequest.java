package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.dto.UpdateAnnotationRelationDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateAnnotationRelationRequest implements Request<AnnotationRelationDto> {
    private final UpdateAnnotationRelationDto annotationRelationDto;

    public UpdateAnnotationRelationRequest(final UpdateAnnotationRelationDto annotationRelationDto) {
        this.annotationRelationDto = annotationRelationDto;
    }

    public UpdateAnnotationRelationDto getAnnotationRelationDto() {
        return this.annotationRelationDto;
    }
}
