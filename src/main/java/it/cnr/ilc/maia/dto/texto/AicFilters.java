package it.cnr.ilc.maia.dto.texto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AicFilters {

    @NonNull
    private String searchValue;
    
    @NonNull
    private String searchMode;
    
    private Integer contextLength;
    
    private Integer index;
    
    private String text;
    
    private String reference;
    
    private String leftContext;
    
    private String annotation;
    
    private String rightContext;

}
