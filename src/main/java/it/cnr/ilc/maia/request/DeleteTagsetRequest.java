package it.cnr.ilc.maia.request;

import it.cnr.ilc.maia.mediator.Request;

public class DeleteTagsetRequest implements Request<Boolean> {

    private final Long tagsetId;

    public DeleteTagsetRequest(final Long tagsetId) {
        this.tagsetId = tagsetId;
    }

    public Long getTagsetId() {
        return this.tagsetId;
    }
}
