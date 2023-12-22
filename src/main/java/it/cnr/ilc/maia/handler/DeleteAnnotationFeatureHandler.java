package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.DeleteAnnotationFeatureRequest;
import it.cnr.ilc.maia.service.AnnotationFeatureService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteAnnotationFeatureHandler implements RequestHandler<DeleteAnnotationFeatureRequest, Boolean> {

    @NonNull
    private final AnnotationFeatureService annotationFeatureService;

    @Override
    public Boolean handle(DeleteAnnotationFeatureRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteAnnotationFeatureRequest request) {
        boolean resutl = annotationFeatureService.delete(request.getAnnotationId());
        return new XResult<>(resutl);
    }
}
