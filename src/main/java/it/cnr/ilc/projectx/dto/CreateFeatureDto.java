package it.cnr.ilc.projectx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.cnr.ilc.projectx.model.FeatureType;
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
public class CreateFeatureDto implements Serializable {

    @NonNull
    private Long layerId;

    @NotBlank
    private String name;

    @NonNull
    private FeatureType type;

    private Long tagsetId;

    private String description;
}
