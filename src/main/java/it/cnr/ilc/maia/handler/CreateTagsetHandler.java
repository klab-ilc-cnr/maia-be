package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.dto.CreateTagsetDto;
import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.CreateTagsetRequest;
import it.cnr.ilc.maia.service.TagsetService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateTagsetHandler implements RequestHandler<CreateTagsetRequest, CreateTagsetDto> {

    @NonNull
    private final TagsetService tagsetService;

    @Override
    public CreateTagsetDto handle(CreateTagsetRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public XResult<CreateTagsetDto> handleXResult(CreateTagsetRequest request) {
        CreateTagsetDto responseDto = tagsetService.saveTagset(request.getTagsetDto());
        return new XResult<>(responseDto);
    }
}
