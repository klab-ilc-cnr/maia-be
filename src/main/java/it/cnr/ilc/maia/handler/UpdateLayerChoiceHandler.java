package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.LayerChoiceDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.UpdateLayerChoiceRequest;
import it.cnr.ilc.maia.service.LayerService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateLayerChoiceHandler implements RequestHandler<UpdateLayerChoiceRequest, LayerChoiceDto> {

    @NonNull
    private final LayerService layerService;

    @Override
    public LayerChoiceDto handle(UpdateLayerChoiceRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<LayerChoiceDto> handleXResult(UpdateLayerChoiceRequest request) {
        LayerChoiceDto layerChoiceDto = layerService.update(request.getUpdateLayerChoiceDto());
        return new XResult(layerChoiceDto);
    }
}
