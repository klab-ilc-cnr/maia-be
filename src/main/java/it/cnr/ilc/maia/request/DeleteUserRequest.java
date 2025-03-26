package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.mediator.Request;

public class DeleteUserRequest implements Request<Long> {

    private final Long userId;

    public DeleteUserRequest(final Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }
}
