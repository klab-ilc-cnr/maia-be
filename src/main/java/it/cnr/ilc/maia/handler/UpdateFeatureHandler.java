package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.FeatureDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.UpdateFeatureRequest;
import it.cnr.ilc.maia.service.FeatureService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateFeatureHandler implements RequestHandler<UpdateFeatureRequest, FeatureDto> {

    @NonNull
    private final FeatureService featureService;

    @Override
    public FeatureDto handle(UpdateFeatureRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<FeatureDto> handleXResult(UpdateFeatureRequest request) {
        FeatureDto responseDto = featureService.updateFeature(request.getFeatureDto());
        return new XResult<>(responseDto);
    }
}
