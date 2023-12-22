package it.cnr.ilc.maia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateAnnotationRelationDto implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private Long srcLayerId;

    @NotNull
    private Long targetLayerId;

    @NotNull
    private Long srcAnnotationId;

    @NotNull
    private Long targetAnnotationId;

    private String description;

    @NotNull
    private Long textId;
}
