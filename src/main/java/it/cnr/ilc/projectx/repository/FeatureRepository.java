package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByLayer_Id(@NotBlank Long layerId);
    Boolean existsByTagset_Id(@NotBlank Long tagsetId);
}
