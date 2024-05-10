package it.cnr.ilc.maia.dto.lexo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DictionaryEntryResponse {

    public DictionaryEntryResponse(DictionaryEntry entry) {
        this.creator = entry.getCreator();
        this.lastUpdate = entry.getLastUpdate();
        this.creationDate = entry.getCreationDate();
        this.id = entry.getDictionaryEntry();
        this.type = entry.getType();
        this.label = entry.getLabel();
        this.pos = entry.getPos();
        this.status = entry.getStatus();
        this.language = entry.getLanguage();
        this.hasChildren = entry.getHasChildren();
        this.sameDictionaryEntryAs = entry.getSameDictionaryEntryAs().entrySet().stream()
                .map(e -> Map.of("id", e.getKey(), "label", e.getValue()))
                .collect(Collectors.toList());
    }

    private String creator;
    private String lastUpdate;
    private String creationDate;
    private String id;
    private String[] type;
    private String label;
    private String[] pos;
    private String status;
    private String language;
    private boolean hasChildren;
    private List sameDictionaryEntryAs;

}
