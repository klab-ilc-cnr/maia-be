package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.AnnotationFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AnnotationFeatureRepository extends JpaRepository<AnnotationFeature, Long> {

    Boolean existsByFeatureId(@NotBlank Long featureId);

    Optional<List<AnnotationFeature>> findByAnnotationId(@NotBlank Long annotationId);

    void deleteByAnnotationId(@NotBlank Long annotationId);

    List<AnnotationFeature> findByLayerId(@NotBlank Long layerId);
}
