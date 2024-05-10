package it.cnr.ilc.maia.dto.lexo;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LexicalEntry {

    private String creator;
    private String lastUpdate;
    private String creationDate;
    private Double confidence;
    private String lexicalEntry;
    private String label;
    private String[] type;
    private String pos;
    private String language;
    private String stemType;
    private List morphology;
    private List links;
    private String author;
    private String revisor;
    private String status;
    private String note;
    private String completionDate;
    private String revisionDate;

}
