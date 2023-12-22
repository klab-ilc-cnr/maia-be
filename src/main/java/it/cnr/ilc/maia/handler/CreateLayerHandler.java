package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.LayerDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.CreateLayerRequest;
import it.cnr.ilc.maia.service.LayerService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateLayerHandler implements RequestHandler<CreateLayerRequest, LayerDto> {

    @NonNull
    private final LayerService layerService;

    @Override
    public LayerDto handle(CreateLayerRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<LayerDto> handleXResult(CreateLayerRequest request) {
        LayerDto responseDto = layerService.saveLayer(request.getLayerDto());
        return new XResult<>(responseDto);
    }
}
