package it.cnr.ilc.maia.repository;

import it.cnr.ilc.maia.model.AnnotationFeature;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnnotationFeatureRepository extends JpaRepository<AnnotationFeature, Long> {

    Boolean existsByFeatureId(@NotBlank Long featureId);

    Optional<List<AnnotationFeature>> findByAnnotationId(@NotBlank Long annotationId);

    void deleteByAnnotationId(@NotBlank Long annotationId);

    List<AnnotationFeature> findByLayerId(@NotBlank Long layerId);
}
