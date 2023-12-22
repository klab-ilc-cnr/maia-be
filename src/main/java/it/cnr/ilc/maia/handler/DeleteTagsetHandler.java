package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.DeleteTagsetRequest;
import it.cnr.ilc.maia.service.TagsetService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteTagsetHandler implements RequestHandler<DeleteTagsetRequest, Boolean> {

    @NonNull
    private final TagsetService tagsetService;

    @Override
    public Boolean handle(DeleteTagsetRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteTagsetRequest request) {
        boolean resutl = tagsetService.delete(request.getTagsetId());
        return new XResult<>(resutl);
    }
}
