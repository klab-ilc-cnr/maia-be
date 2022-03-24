package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.mediator.Request;

public class CreateUser implements Request<CreateUserDto> {
    private final CreateUserDto user;

    public CreateUser(final CreateUserDto user) {
        this.user = user;
    }

    public CreateUserDto getUser() {
        return this.user;
    }
}
