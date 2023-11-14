package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.UpdateTagsetDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateTagsetRequest implements Request<UpdateTagsetDto> {

    private final UpdateTagsetDto updateTagsetDto;

    public UpdateTagsetRequest(final UpdateTagsetDto updateTagsetDto) {
        this.updateTagsetDto = updateTagsetDto;
    }

    public UpdateTagsetDto getTagsetDto() {
        return this.updateTagsetDto;
    }
}
