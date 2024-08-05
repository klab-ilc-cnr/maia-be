package it.cnr.ilc.maia.dto.lexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LexicographicComponent {

    private String creator;
    private String lastUpdate;
    private String creationDate;
    private Double confidence;
    private String component;
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
    private String[] lexicalConcepts;

}
