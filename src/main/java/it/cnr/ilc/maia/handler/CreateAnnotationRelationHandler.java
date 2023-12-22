package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.AnnotationRelationDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.CreateAnnotationRelationRequest;
import it.cnr.ilc.maia.service.AnnotationRelationService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateAnnotationRelationHandler implements RequestHandler<CreateAnnotationRelationRequest, AnnotationRelationDto> {

    @NonNull
    private final AnnotationRelationService annotationRelationService;

    @Override
    public AnnotationRelationDto handle(CreateAnnotationRelationRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<AnnotationRelationDto> handleXResult(CreateAnnotationRelationRequest request) {
        AnnotationRelationDto responseDto = annotationRelationService.save(request.getAnnotationRelationDto());
        return new XResult<>(responseDto);
    }
}
