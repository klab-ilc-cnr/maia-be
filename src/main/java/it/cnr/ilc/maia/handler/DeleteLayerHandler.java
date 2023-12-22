package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.model.Layer;
import it.cnr.ilc.maia.request.DeleteLayerRequest;
import it.cnr.ilc.maia.service.LayerService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteLayerHandler implements RequestHandler<DeleteLayerRequest, Boolean> {

    @NonNull
    private final LayerService layerService;

    @Override
    public Boolean handle(DeleteLayerRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteLayerRequest request) {
        Layer layer = layerService.retrieveLayer(request.getLayerId());
        Boolean resutl = layerService.delete(layer);
        return new XResult<>(resutl);
    }
}
