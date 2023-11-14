package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.Tagset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsetRepository extends JpaRepository<Tagset, Long> {
}
