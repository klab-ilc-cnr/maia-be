package it.cnr.ilc.maia.dto.lexo;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DictionaryEntry {

    private String creator;
    private String lastUpdate;
    private String creationDate;
    private Double confidence;
    private String dictionaryEntry;
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
    private Map<String, String> seeAlso;
    private Map<String, String> sameDictionaryEntryAs;

}
