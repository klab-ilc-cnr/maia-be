package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.DeleteFeatureRequest;
import it.cnr.ilc.maia.service.FeatureService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteFeatureHandler implements RequestHandler<DeleteFeatureRequest, Boolean> {

    @NonNull
    private final FeatureService featureService;

    @Override
    public Boolean handle(DeleteFeatureRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<Boolean> handleXResult(DeleteFeatureRequest request) {
        boolean resutl = featureService.delete(request.getLayerId(), request.getFeatureId());
        return new XResult<>(resutl);
    }
}
