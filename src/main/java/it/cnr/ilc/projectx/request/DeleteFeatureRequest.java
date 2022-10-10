package it.cnr.ilc.projectx.request;

import it.cnr.ilc.projectx.mediator.Request;

public class DeleteTagsetRequest implements Request<Long> {
    private final Long tagsetId;

    public DeleteTagsetRequest(final Long tagsetId) {
        this.tagsetId = tagsetId;
    }

    public Long getTagsetId() {
        return this.tagsetId;
    }
}
