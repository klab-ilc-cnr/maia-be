package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.DeleteAnnotationRelationRequest;
import it.cnr.ilc.maia.service.AnnotationRelationService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteAnnotationRelationHandler implements RequestHandler<DeleteAnnotationRelationRequest, Boolean> {

    @NonNull
    private final AnnotationRelationService annotationRelationService;

    @Override
    public Boolean handle(DeleteAnnotationRelationRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteAnnotationRelationRequest request) {
        Boolean resutl = annotationRelationService.delete(request.getRelationId());
        return new XResult<>(resutl);
    }
}
