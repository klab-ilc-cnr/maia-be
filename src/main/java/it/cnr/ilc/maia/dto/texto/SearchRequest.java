package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {

    private List<Long> resources;
    
    private Boolean reload;

    @NonNull
    private Integer start;

    @NonNull
    private Integer end;

    @NonNull
    private SearchFilters filters;

}
