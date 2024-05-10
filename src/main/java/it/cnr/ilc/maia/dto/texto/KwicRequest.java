package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KwicRequest {

    private List<Long> resources;
    
    @NonNull
    private Integer start;
    
    @NonNull
    private Integer end;
    
    @NonNull
    private KwicFilters filters;

}
