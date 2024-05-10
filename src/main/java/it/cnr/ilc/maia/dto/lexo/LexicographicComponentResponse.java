package it.cnr.ilc.maia.dto.lexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LexicographicComponentResponse {

    public LexicographicComponentResponse(LexicographicComponent component) {
        creator = component.getCreator();
        lastUpdate = component.getLastUpdate();
        creationDate = component.getCreationDate();
        confidence = component.getConfidence();
        id = component.getComponent();
        position = component.getPosition();
        referredEntity = component.getReferredEntity();
        type = component.getType();
        label = component.getLabel();
        pos = component.getPos();
        status = component.getStatus();
        revisor = component.getRevisor();
        author = component.getAuthor();
        note = component.getNote();
        hasChildren = component.getHasChildren();
        completionDate = component.getCompletionDate();
        revisionDate = component.getRevisionDate();
    }

    private String creator;
    private String lastUpdate;
    private String creationDate;
    private Double confidence;
    private String id;
    private Integer position;
    private String referredEntity;
    private String[] type;
    private String label;
    private String[] pos;
    private String status;
    private String revisor;
    private String author;
    private String note;
    private Boolean hasChildren;
    private String completionDate;
    private String revisionDate;

}
