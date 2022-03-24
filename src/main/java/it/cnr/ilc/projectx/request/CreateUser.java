package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.Request;
import it.cnr.ilc.projectx.mediator.Response;
import it.cnr.ilc.projectx.xresults.XResult;

public class CreateUser implements Request<UserDto> {
    private final UserDto user;

    public CreateUser(final UserDto user) {
        this.user = user;
    }

    public UserDto getUser() {
        return this.user;
    }
}
