package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.UpdateTagsetDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.UpdateTagsetRequest;
import it.cnr.ilc.maia.service.TagsetService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateTagsetHandler implements RequestHandler<UpdateTagsetRequest, UpdateTagsetDto> {

    @NonNull
    private final TagsetService tagsetService;

    @Override
    public UpdateTagsetDto handle(UpdateTagsetRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<UpdateTagsetDto> handleXResult(UpdateTagsetRequest request) {
        UpdateTagsetDto responseDto = tagsetService.updateTagset(request.getTagsetDto());
        return new XResult<>(responseDto);
    }
}
