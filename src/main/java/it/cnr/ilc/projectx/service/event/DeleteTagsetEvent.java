package it.cnr.ilc.projectx.service.event;

import org.springframework.context.ApplicationEvent;

public class DeleteTagsetEvent extends ApplicationEvent {
    private Long tagsetId;

    public DeleteTagsetEvent(Object source, final Long tagsetId) {
        super(source);
        this.tagsetId = tagsetId;
    }

    public Long getTagsetId() {
        return tagsetId;
    }
}
