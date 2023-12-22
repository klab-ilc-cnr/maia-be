package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.AnnotationRelationDto;
import it.cnr.ilc.maia.dto.FeatureDto;
import it.cnr.ilc.maia.dto.UpdateAnnotationRelationDto;
import it.cnr.ilc.maia.mediator.Request;

public class UpdateAnnotationRelationRequest implements Request<AnnotationRelationDto> {

    private final UpdateAnnotationRelationDto annotationRelationDto;

    public UpdateAnnotationRelationRequest(final UpdateAnnotationRelationDto annotationRelationDto) {
        this.annotationRelationDto = annotationRelationDto;
    }

    public UpdateAnnotationRelationDto getAnnotationRelationDto() {
        return this.annotationRelationDto;
    }
}
