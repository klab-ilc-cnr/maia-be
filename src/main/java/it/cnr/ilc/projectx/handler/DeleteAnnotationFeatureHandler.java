package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.DeleteAnnotationFeatureRequest;
import it.cnr.ilc.projectx.request.DeleteFeatureRequest;
import it.cnr.ilc.projectx.service.AnnotationFeatureService;
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
public class DeleteAnnotationFeatureHandler implements RequestHandler<DeleteAnnotationFeatureRequest, Boolean> {
    @NonNull
    private final AnnotationFeatureService annotationFeatureService;

    @Override
    public Boolean handle(DeleteAnnotationFeatureRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteAnnotationFeatureRequest request) {
        try {
            boolean resutl = annotationFeatureService.delete(request.getAnnotationId());

            return new XResult<>(resutl);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
