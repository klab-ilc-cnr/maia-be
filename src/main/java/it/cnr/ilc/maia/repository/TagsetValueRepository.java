package it.cnr.ilc.maia.repository;

import it.cnr.ilc.maia.model.TagsetValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsetValueRepository extends JpaRepository<TagsetValue, Long> {
}
