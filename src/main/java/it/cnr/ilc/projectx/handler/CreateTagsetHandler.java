package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.dto.CreateTagsetDto;
import it.cnr.ilc.projectx.dto.TagsetDto;
import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.CreateTagsetRequest;
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
public class CreateTagsetHandler implements RequestHandler<CreateTagsetRequest, CreateTagsetDto> {
    @NonNull
    private final TagsetService tagsetService;

    @Override
    public CreateTagsetDto handle(CreateTagsetRequest request) {
        throw new NotImplementedYetException();
    }

    @Transactional
    @Override
    public XResult<CreateTagsetDto> handleXResult(CreateTagsetRequest request) {
        try {
            CreateTagsetDto responseDto = tagsetService.saveTagset(request.getTagsetDto());

            return new XResult<>(responseDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
