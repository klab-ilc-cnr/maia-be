package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.DeleteTagsetRequest;
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
public class DeleteTagsetHandler implements RequestHandler<DeleteTagsetRequest, Boolean> {
    @NonNull
    private final TagsetService tagsetService;

    @Override
    public Boolean handle(DeleteTagsetRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteTagsetRequest request) {
        try {
            boolean resutl = tagsetService.delete(request.getTagsetId());

            return new XResult<>(resutl);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
