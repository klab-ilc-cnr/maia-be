package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.SavePanelLayoutRequest;
import it.cnr.ilc.projectx.service.WorkspaceService;
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
public class SavePanelLayoutHandler implements RequestHandler<SavePanelLayoutRequest, Void> {
    @NonNull
    private final WorkspaceService workspaceService;

    @Override
    public Void handle(SavePanelLayoutRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Void> handleXResult(SavePanelLayoutRequest request) {
        try {
            workspaceService.savePanelLayout(request.getWorkspaceId(), request.getLayout());

            return new XResult<>();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
