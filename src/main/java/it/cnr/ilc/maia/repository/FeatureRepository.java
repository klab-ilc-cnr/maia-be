package it.cnr.ilc.maia.repository;

import it.cnr.ilc.maia.model.Feature;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

    List<Feature> findByLayer_Id(@NotBlank Long layerId);

    Boolean existsByTagset_Id(@NotBlank Long tagsetId);
}
