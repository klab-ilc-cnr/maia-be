package it.cnr.ilc.projectx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateTagsetValuesDto {

    @NonNull
    private Long id;

    @NotBlank
    private String name;

    private String description;
}
