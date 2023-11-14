package it.cnr.ilc.projectx.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Table(name = "annotation_feature")
@IdClass(AnnotationFeatureId.class)
public class AnnotationFeature implements Serializable {

    @Id
    @NonNull
    private Long annotationId;
    @Id
    @NonNull
    private Long featureId;

    @NonNull
    private Long layerId;
}
