package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.AnnotationFeatureDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.CreateAnnotationFeatureRequest;
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
public class CreateAnnotationFeaturerHandler implements RequestHandler<CreateAnnotationFeatureRequest, AnnotationFeatureDto> {
    @NonNull
    private final AnnotationFeatureService annotationFeatureService;

    @Override
    public AnnotationFeatureDto handle(CreateAnnotationFeatureRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<AnnotationFeatureDto> handleXResult(CreateAnnotationFeatureRequest request) {
        try {
            AnnotationFeatureDto responseDto = annotationFeatureService.save(request.getAnnotationFeatureDto());

            return new XResult<>(responseDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}