package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class KwicRequest {

    private List<Long> resources;

    private Long layerId;

    private Boolean reload;
    
    @NonNull
    private Integer start;

    @NonNull
    private Integer end;

    @NonNull
    private KwicFilters filters;

}
