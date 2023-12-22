package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.AnnotationFeatureDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.UpdateAnnotationFeatureRequest;
import it.cnr.ilc.maia.service.AnnotationFeatureService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateAnnotationFeatureHandler implements RequestHandler<UpdateAnnotationFeatureRequest, AnnotationFeatureDto> {

    @NonNull
    private final AnnotationFeatureService annotationFeatureService;

    @Override
    public AnnotationFeatureDto handle(UpdateAnnotationFeatureRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<AnnotationFeatureDto> handleXResult(UpdateAnnotationFeatureRequest request) {
        AnnotationFeatureDto responseDto = annotationFeatureService.update(request.getAnnotationFeatureDto());
        return new XResult<>(responseDto);
    }
}
