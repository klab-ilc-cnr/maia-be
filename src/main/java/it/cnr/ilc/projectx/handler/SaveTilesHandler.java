package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.SaveTilesRequest;
import it.cnr.ilc.projectx.service.TileService;
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
public class SaveTilesHandler implements RequestHandler<SaveTilesRequest, Void> {
    @NonNull
    private final TileService tileService;

    @Override
    public Void handle(SaveTilesRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Void> handleXResult(SaveTilesRequest request) {
        try {
            tileService.saveTiles(request.getWorkspaceId(), request.getTiles());

            return new XResult<>();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
