package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.UpdateFeatureRequest;
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
public class UpdateFeatureHandler implements RequestHandler<UpdateFeatureRequest, FeatureDto> {
    @NonNull
    private final FeatureService featureService;

    @Override
    public FeatureDto handle(UpdateFeatureRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<FeatureDto> handleXResult(UpdateFeatureRequest request) {
        try {
            FeatureDto responseDto = featureService.updateFeature(request.getFeatureDto());

            return new XResult<>(responseDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
