package it.cnr.ilc.maia.dto.texto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SearchFilters {

    @NonNull
    private String searchValue;

    @NonNull
    private String searchMode;

    private String[] semantics;

    private String[] poss;

    private String[] namedEntities;

    private String cooccurValue;

    private Integer contextLength;

    private Integer index;

    private String text;

    private String reference;

    private String leftContext;

    private String kwic;

    private String rightContext;

}
