package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.AnnotationFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface AnnotationFeatureRepository extends JpaRepository<AnnotationFeature, Long> {

    Boolean existsByFeatureId(@NotBlank Long featureId);
}
