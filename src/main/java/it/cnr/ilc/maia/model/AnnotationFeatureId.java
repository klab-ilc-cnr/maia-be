package it.cnr.ilc.maia.model;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AnnotationFeatureId implements Serializable {

    private Long annotationId;
    private Long featureId;
}
