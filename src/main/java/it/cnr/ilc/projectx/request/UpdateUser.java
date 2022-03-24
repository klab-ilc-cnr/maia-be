package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.Request;

public class UpdateUser implements Request<UserDto> {
    private final UserDto user;

    public UpdateUser(final UserDto user) {
        this.user = user;
    }

    public UserDto getUser() {
        return this.user;
    }
}
