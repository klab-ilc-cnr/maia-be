package it.cnr.ilc.maia.dto.lexo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DictionaryEntryCoreResponse {

    public DictionaryEntryCoreResponse(DictionaryEntryCore entry) {
        this.creator = entry.getCreator();
        this.lastUpdate = entry.getLastUpdate();
        this.creationDate = entry.getCreationDate();
        this.confidence = entry.getConfidence();
        this.id = entry.getDictionaryEntry();
        this.type = entry.getType();
        this.label = entry.getLabel();
        this.pos = entry.getPos();
        this.status = entry.getStatus();
        this.revisor = entry.getRevisor();
        this.author = entry.getAuthor();
        this.completionDate = entry.getCompletionDate();
        this.revisionDate = entry.getRevisionDate();
        this.language = entry.getLanguage();
        this.images = entry.getImages();
        this.hasChildren = entry.getHasChildren();
        this.note = entry.getNote();
        this.seeAlso = entry.getSeeAlso();
        this.sameDictionaryEntryAs = entry.getSameDictionaryEntryAs().stream()
                .filter(e -> Boolean.FALSE.equals(e.get("inferred")))
                .map(e -> Map.of("id", e.get("entity"), "label", e.get("label")))
                .collect(Collectors.toList());
    }

    private String creator;
    private String lastUpdate;
    private String creationDate;
    private double confidence;
    private String id;
    private String[] type;
    private String label;
    private String[] pos;
    private String status;
    private String revisor;
    private String author;
    private String completionDate;
    private String revisionDate;
    private String language;
    private String[] images;
    private Boolean hasChildren;
    private String note;
    private List seeAlso;
    private List sameDictionaryEntryAs;

}
