package it.cnr.ilc.projectx.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "annotation_feature")
@IdClass(AnnotationFeatureId.class)
public class AnnotationFeature implements Serializable {

    @Id
    @NonNull
    private Long annotationId;
    @Id
    @NonNull
    private Long featureId;

    @Override
    public String toString() {
        return "AnnotationFeature{" +
                "annotation_id=" + annotationId +
                ", feature_id='" + featureId + '\'' +
                '}';
    }
}

