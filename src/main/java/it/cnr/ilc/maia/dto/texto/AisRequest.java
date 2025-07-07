package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AisRequest {

    private List<Long> resources;

    @NonNull
    private Integer start;

    @NonNull
    private Integer end;

    @NonNull
    private AisFilters filters;

}
