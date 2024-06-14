package it.cnr.ilc.maia.dto.lexo;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericEntity {

    private String entity;
    private String label;
    private List entityType;
    private Boolean inferred;
    private String linkType;
    private String link;
}
