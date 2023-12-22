package it.cnr.ilc.maia.service.event;

import org.springframework.context.ApplicationEvent;

public class CanTagsetBeDeletedEvent extends ApplicationEvent {

    private Long tagsetId;

    public CanTagsetBeDeletedEvent(Object source, final Long tagsetId) {
        super(source);
        this.tagsetId = tagsetId;
    }

    public Long getTagsetId() {
        return tagsetId;
    }
}
