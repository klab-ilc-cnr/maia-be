package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.AnnotationRelationDto;
import it.cnr.ilc.maia.dto.CreateAnnotationRelationDto;
import it.cnr.ilc.maia.mediator.Request;

public class CreateAnnotationRelationRequest implements Request<AnnotationRelationDto> {

    private final CreateAnnotationRelationDto annotationRelationDto;

    public CreateAnnotationRelationRequest(final CreateAnnotationRelationDto annotationRelationDto) {
        this.annotationRelationDto = annotationRelationDto;
    }

    public CreateAnnotationRelationDto getAnnotationRelationDto() {
        return this.annotationRelationDto;
    }
}
