package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.LayerChoiceDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.UpdateLayerChoiceRequest;
import it.cnr.ilc.projectx.service.LayerService;
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
public class UpdateLayerChoiceHandler implements RequestHandler<UpdateLayerChoiceRequest, LayerChoiceDto> {
    @NonNull
    private final LayerService layerService;

    @Override
    public LayerChoiceDto handle(UpdateLayerChoiceRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<LayerChoiceDto> handleXResult(UpdateLayerChoiceRequest request) {
        try {
            LayerChoiceDto layerChoiceDto = layerService.update(request.getUpdateLayerChoiceDto());

            return new XResult(layerChoiceDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}