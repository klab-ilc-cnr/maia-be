package it.cnr.ilc.projectx.repository;

import it.cnr.ilc.projectx.model.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TileRepository extends JpaRepository<Tile, Long> {

    List<Tile> findAllByWorkspaceId(Long workspaceId);
}
