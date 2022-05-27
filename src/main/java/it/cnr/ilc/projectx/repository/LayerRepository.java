package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayerRepository extends JpaRepository<Layer, Long> {
}
