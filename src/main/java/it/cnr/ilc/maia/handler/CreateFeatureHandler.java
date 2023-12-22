package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.CreateFeatureDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.CreateFeatureRequest;
import it.cnr.ilc.maia.service.FeatureService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateFeatureHandler implements RequestHandler<CreateFeatureRequest, CreateFeatureDto> {

    @NonNull
    private final FeatureService featureService;

    @Override
    public CreateFeatureDto handle(CreateFeatureRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<CreateFeatureDto> handleXResult(CreateFeatureRequest request) {
        CreateFeatureDto responseDto = featureService.saveFeature(request.getFeatureDto());
        return new XResult<>(responseDto);
    }
}
