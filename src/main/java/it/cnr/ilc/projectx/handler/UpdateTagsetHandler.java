package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.UpdateTagsetDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.UpdateTagsetRequest;
import it.cnr.ilc.projectx.service.TagsetService;
import it.cnr.ilc.projectx.xresults.XResult;
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
