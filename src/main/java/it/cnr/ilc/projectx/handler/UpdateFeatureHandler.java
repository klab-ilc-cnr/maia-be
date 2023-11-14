package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.UpdateFeatureRequest;
import it.cnr.ilc.projectx.service.FeatureService;
import it.cnr.ilc.projectx.xresults.XResult;
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
