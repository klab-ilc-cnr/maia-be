package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.CreateTagsetDto;
import it.cnr.ilc.maia.mediator.Request;

public class CreateTagsetRequest implements Request<CreateTagsetDto> {

    private final CreateTagsetDto createTagsetDto;

    public CreateTagsetRequest(final CreateTagsetDto createTagsetDto) {
        this.createTagsetDto = createTagsetDto;
    }

    public CreateTagsetDto getTagsetDto() {
        return this.createTagsetDto;
    }
}
