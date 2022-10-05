package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.CreateTagsetDto;
import it.cnr.ilc.projectx.mediator.Request;

public class CreateTagsetRequest implements Request<CreateTagsetDto> {
    private final CreateTagsetDto createTagsetDto;

    public CreateTagsetRequest(final CreateTagsetDto createTagsetDto) {
        this.createTagsetDto = createTagsetDto;
    }

    public CreateTagsetDto getTagsetDto() {
        return this.createTagsetDto;
    }
}
