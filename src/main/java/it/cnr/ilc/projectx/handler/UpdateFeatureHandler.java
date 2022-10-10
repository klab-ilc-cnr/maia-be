package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.CreateTagsetDto;
import it.cnr.ilc.projectx.dto.UpdateTagsetDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.CreateTagsetRequest;
import it.cnr.ilc.projectx.request.UpdateTagsetRequest;
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
public class UpdateTagsetHandler implements RequestHandler<UpdateTagsetRequest, UpdateTagsetDto> {
    @NonNull
    private final TagsetService tagsetService;

    @Override
    public UpdateTagsetDto handle(UpdateTagsetRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<UpdateTagsetDto> handleXResult(UpdateTagsetRequest request) {
        try {
            UpdateTagsetDto responseDto = tagsetService.updateTagset(request.getTagsetDto());

            return new XResult<>(responseDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
