package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.AnnotationRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnnotationRelationRepository extends JpaRepository<AnnotationRelation, Long> {
    Boolean existsBySrcLayer_IdOrTargetLayer_Id(@NotNull Long srcLayerId, @NotNull Long targetLayerId);

    Optional<List<AnnotationRelation>> findByTextId(Long textId);
}
