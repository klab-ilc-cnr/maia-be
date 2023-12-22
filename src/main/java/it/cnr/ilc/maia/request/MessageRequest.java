package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.mediator.Request;

public class MessageRequest implements Request<String> {

    private final String message;

    public MessageRequest(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
