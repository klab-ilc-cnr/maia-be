package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.SaveWorkspaceRequest;
import it.cnr.ilc.maia.service.TileService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SaveWorkspaceHandler implements RequestHandler<SaveWorkspaceRequest, Void> {

    @NonNull
    private final TileService tileService;

    @Override
    public Void handle(SaveWorkspaceRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<Void> handleXResult(SaveWorkspaceRequest request) {
        tileService.saveWorkspace(request.getWorkspace());
        return new XResult<>();
    }
}
