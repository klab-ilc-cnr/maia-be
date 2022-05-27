package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.LayerDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.CreateLayerRequest;
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
public class CreateLayerHandler implements RequestHandler<CreateLayerRequest, LayerDto> {
    @NonNull
    private final LayerService layerService;

    @Override
    public LayerDto handle(CreateLayerRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<LayerDto> handleXResult(CreateLayerRequest request) {
        try {
            LayerDto responseDto = layerService.saveLayer(request.getLayerDto());

            return new XResult<>(responseDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
