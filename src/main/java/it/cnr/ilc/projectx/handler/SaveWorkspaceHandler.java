package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.SaveWorkspaceRequest;
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
public class SaveWorkspaceHandler implements RequestHandler<SaveWorkspaceRequest, Void> {
    @NonNull
    private final TileService tileService;

    @Override
    public Void handle(SaveWorkspaceRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Void> handleXResult(SaveWorkspaceRequest request) {
        try {
            tileService.saveWorkspace(request.getWorkspace());

            return new XResult<>();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
