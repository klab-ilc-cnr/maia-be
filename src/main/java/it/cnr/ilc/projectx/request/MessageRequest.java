package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.mediator.Request;

public class MessageRequest implements Request<String> {
    private final String message;

    public MessageRequest(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
