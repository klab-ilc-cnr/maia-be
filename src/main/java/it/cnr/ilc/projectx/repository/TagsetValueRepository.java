package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.Tagset;
import it.cnr.ilc.projectx.model.TagsetValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsetValueRepository extends JpaRepository<TagsetValue, Long> {
}
