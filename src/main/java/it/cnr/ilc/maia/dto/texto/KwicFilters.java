package it.cnr.ilc.maia.dto.texto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class KwicFilters {

    @NonNull
    private String searchValue;

    @NonNull
    private String searchMode;

    private Integer contextLength;

    private Integer index;

    private String text;

    private String reference;

    private String leftContext;

    private String kwic;

    private String rightContext;
    
    private Boolean annotated;

}
