package it.cnr.ilc.projectx.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AnnotationFeatureId implements Serializable {
    private Long annotationId;
    private Long featureId;
}
