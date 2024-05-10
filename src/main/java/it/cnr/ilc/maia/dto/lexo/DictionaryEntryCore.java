package it.cnr.ilc.maia.dto.lexo;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DictionaryEntryCore {

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
    private String[] note;
    private List seeAlso;
    private List<Map<String, Object>> sameDictionaryEntryAs;

}
