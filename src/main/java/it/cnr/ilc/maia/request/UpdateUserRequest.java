package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.dto.UpdateUserDto;
import it.cnr.ilc.maia.dto.UserDto;
import it.cnr.ilc.maia.mediator.Request;

public class UpdateUserRequest implements Request<UserDto> {

    private final UpdateUserDto user;

    public UpdateUserRequest(final UpdateUserDto user) {
        this.user = user;
    }

    public UpdateUserDto getUser() {
        return this.user;
    }
}
