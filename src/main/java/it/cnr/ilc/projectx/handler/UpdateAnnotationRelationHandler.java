package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.UpdateAnnotationRelationRequest;
import it.cnr.ilc.projectx.service.AnnotationRelationService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateAnnotationRelationHandler implements RequestHandler<UpdateAnnotationRelationRequest, AnnotationRelationDto> {

    @NonNull
    private final AnnotationRelationService annotationRelationService;

    @Override
    public AnnotationRelationDto handle(UpdateAnnotationRelationRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<AnnotationRelationDto> handleXResult(UpdateAnnotationRelationRequest request) {
        AnnotationRelationDto responseDto = annotationRelationService.update(request.getAnnotationRelationDto());
        return new XResult<>(responseDto);
    }
}
