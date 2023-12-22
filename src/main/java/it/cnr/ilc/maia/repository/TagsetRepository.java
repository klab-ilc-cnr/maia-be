package it.cnr.ilc.maia.repository;

import it.cnr.ilc.maia.model.Tagset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsetRepository extends JpaRepository<Tagset, Long> {
}
