package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.request.DeleteLayerRequest;
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
public class DeleteLayerHandler implements RequestHandler<DeleteLayerRequest, Long> {
    @NonNull
    private final LayerService layerService;

    @Override
    public Long handle(DeleteLayerRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Long> handleXResult(DeleteLayerRequest request) {
        try {
            Layer layer = layerService.retrieveLayer(request.getLayerId());

            layerService.delete(layer);

            return new XResult<>(request.getLayerId());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
