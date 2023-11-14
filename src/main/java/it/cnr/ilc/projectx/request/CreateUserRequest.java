package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.mediator.Request;

public class CreateUserRequest implements Request<CreateUserDto> {

    private final CreateUserDto user;

    public CreateUserRequest(final CreateUserDto user) {
        this.user = user;
    }

    public CreateUserDto getUser() {
        return this.user;
    }
}
