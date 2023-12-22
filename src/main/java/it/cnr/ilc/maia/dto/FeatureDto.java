package it.cnr.ilc.maia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.cnr.ilc.maia.model.FeatureType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class FeatureDto implements Serializable {

    @NonNull
    private Long id;

    @NonNull
    private Long layerId;

    @NotBlank
    private String name;

    @NonNull
    private FeatureType type;

    private TagsetDto tagset;

    private String description;
}
