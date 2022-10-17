package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.dto.CreateAnnotationRelationDto;
import it.cnr.ilc.projectx.mediator.Request;

public class CreateAnnotationRelationRequest implements Request<AnnotationRelationDto> {
    private final CreateAnnotationRelationDto annotationRelationDto;

    public CreateAnnotationRelationRequest(final CreateAnnotationRelationDto annotationRelationDto) {
        this.annotationRelationDto = annotationRelationDto;
    }

    public CreateAnnotationRelationDto getAnnotationRelationDto() {
        return this.annotationRelationDto;
    }
}
