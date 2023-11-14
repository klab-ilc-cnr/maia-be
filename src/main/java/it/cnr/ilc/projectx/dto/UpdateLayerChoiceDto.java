package it.cnr.ilc.projectx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateLayerChoiceDto implements Serializable {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String color;

    private String description;

}
