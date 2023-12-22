package it.cnr.ilc.maia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AnnotationFeatureDto implements Serializable {

    @NonNull
    private Long annotationId;

    @NonNull
    private Long layerId;

    @NonNull
    private List<Long> featureIds;

}
