package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.CreateUserDto;
import it.cnr.ilc.maia.mediator.Request;

public class CreateUserRequest implements Request<CreateUserDto> {

    private final CreateUserDto user;

    public CreateUserRequest(final CreateUserDto user) {
        this.user = user;
    }

    public CreateUserDto getUser() {
        return this.user;
    }
}
