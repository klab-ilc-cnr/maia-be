package it.cnr.ilc.maia.repository;

import it.cnr.ilc.maia.model.AnnotationRelation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnnotationRelationRepository extends JpaRepository<AnnotationRelation, Long> {

    Boolean existsBySrcLayer_IdOrTargetLayer_Id(@NotNull Long srcLayerId, @NotNull Long targetLayerId);

    Optional<List<AnnotationRelation>> findByTextId(Long textId);
}
