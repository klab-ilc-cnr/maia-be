package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.DeleteFeatureRequest;
import it.cnr.ilc.projectx.service.FeatureService;
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
public class DeleteFeatureHandler implements RequestHandler<DeleteFeatureRequest, Boolean> {
    @NonNull
    private final FeatureService featureService;

    @Override
    public Boolean handle(DeleteFeatureRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteFeatureRequest request) {
        try {
            boolean resutl = featureService.deleteFeature(request.getLayerId(), request.getFeatureId());

            return new XResult<>(resutl);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
