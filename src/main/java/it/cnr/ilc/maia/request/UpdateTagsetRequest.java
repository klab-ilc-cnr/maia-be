package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.UpdateTagsetDto;
import it.cnr.ilc.maia.mediator.Request;

public class UpdateTagsetRequest implements Request<UpdateTagsetDto> {

    private final UpdateTagsetDto updateTagsetDto;

    public UpdateTagsetRequest(final UpdateTagsetDto updateTagsetDto) {
        this.updateTagsetDto = updateTagsetDto;
    }

    public UpdateTagsetDto getTagsetDto() {
        return this.updateTagsetDto;
    }
}
