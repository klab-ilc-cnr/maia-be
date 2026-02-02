package it.cnr.ilc.maia.dto.texto;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AnnotationMultipleRequest {

    @NonNull
    private Long layerId;

    @NonNull
    private List<AnnotationFeatureUpdate> features;
    
    @NonNull
    private List<AnnotationOffset> offsets;
    
}
