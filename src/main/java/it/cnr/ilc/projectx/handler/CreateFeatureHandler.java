package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.CreateFeatureDto;
import it.cnr.ilc.projectx.dto.CreateTagsetDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.CreateFeatureRequest;
import it.cnr.ilc.projectx.request.CreateTagsetRequest;
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
public class CreateFeatureHandler implements RequestHandler<CreateFeatureRequest, CreateFeatureDto> {
    @NonNull
    private final FeatureService featureService;

    @Override
    public CreateFeatureDto handle(CreateFeatureRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<CreateFeatureDto> handleXResult(CreateFeatureRequest request) {
        try {
            CreateFeatureDto responseDto = featureService.saveFeature(request.getFeatureDto());

            return new XResult<>(responseDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
