package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.model.Tagset;
import it.cnr.ilc.projectx.request.DeleteLayerRequest;
import it.cnr.ilc.projectx.request.DeleteTagsetRequest;
import it.cnr.ilc.projectx.service.LayerService;
import it.cnr.ilc.projectx.service.TagsetService;
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
public class DeleteTagsetHandler implements RequestHandler<DeleteTagsetRequest, Long> {
    @NonNull
    private final TagsetService tagsetService;

    @Override
    public Long handle(DeleteTagsetRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Long> handleXResult(DeleteTagsetRequest request) {
        try {
            tagsetService.deleteTagset(request.getTagsetId());

            return new XResult<>(request.getTagsetId());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
