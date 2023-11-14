package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.UpdateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateUserRequest implements Request<UserDto> {

    private final UpdateUserDto user;

    public UpdateUserRequest(final UpdateUserDto user) {
        this.user = user;
    }

    public UpdateUserDto getUser() {
        return this.user;
    }
}
