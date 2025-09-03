package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SearchFilterValuesRequest {

    private List<Long> resources;

    private Integer start;

    private Integer end;

    @NonNull
    private SearchFilters filters;

}
