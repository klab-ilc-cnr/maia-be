package it.cnr.ilc.maia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class TagsetDto implements Serializable {

    @NonNull
    private Long id;

    @NotBlank
    private String name;

    private String description;

    private List<TagsetValuesDto> values;
}
