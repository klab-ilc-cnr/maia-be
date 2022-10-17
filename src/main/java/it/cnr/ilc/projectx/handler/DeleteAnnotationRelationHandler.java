package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.request.DeleteAnnotationRelationRequest;
import it.cnr.ilc.projectx.service.AnnotationRelationService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.spi.NotImplementedYetException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteAnnotationRelationHandler implements RequestHandler<DeleteAnnotationRelationRequest, Boolean> {
    @NonNull
    private final AnnotationRelationService annotationRelationService;

    @Override
    public Boolean handle(DeleteAnnotationRelationRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteAnnotationRelationRequest request) {
        try {
            Boolean resutl = annotationRelationService.delete(request.getRelationId());

            return new XResult<>(resutl);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
