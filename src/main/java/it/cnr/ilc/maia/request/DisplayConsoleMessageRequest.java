package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.mediator.Request;

public class DisplayConsoleMessageRequest implements Request<Void> {

    private final String message;

    public DisplayConsoleMessageRequest(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
