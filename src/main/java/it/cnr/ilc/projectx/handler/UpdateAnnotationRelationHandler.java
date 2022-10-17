package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.UpdateAnnotationRelationRequest;
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
public class UpdateAnnotationRelationHandler implements RequestHandler<UpdateAnnotationRelationRequest, AnnotationRelationDto> {
    @NonNull
    private final AnnotationRelationService annotationRelationService;

    @Override
    public AnnotationRelationDto handle(UpdateAnnotationRelationRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<AnnotationRelationDto> handleXResult(UpdateAnnotationRelationRequest request) {
        try {
            AnnotationRelationDto responseDto = annotationRelationService.update(request.getAnnotationRelationDto());

            return new XResult<>(responseDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
