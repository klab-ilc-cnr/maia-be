package it.cnr.ilc.projectx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class AnnotationRelationDto implements Serializable {
    @NotNull
    private Long id;

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
