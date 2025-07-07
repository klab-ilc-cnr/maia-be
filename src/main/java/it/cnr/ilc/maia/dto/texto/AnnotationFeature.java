package it.cnr.ilc.maia.dto.texto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnotationFeature {

    @NotNull
    private Long featureId;

    @NotNull
    private String value;

}
