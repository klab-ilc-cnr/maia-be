package it.cnr.ilc.maia.repository;

import it.cnr.ilc.maia.model.Layer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayerRepository extends JpaRepository<Layer, Long> {
}
