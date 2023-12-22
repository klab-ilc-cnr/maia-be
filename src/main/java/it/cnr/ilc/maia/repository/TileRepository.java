package it.cnr.ilc.maia.repository;

import it.cnr.ilc.maia.model.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TileRepository extends JpaRepository<Tile, Long> {

    List<Tile> findAllByWorkspaceId(Long workspaceId);
}
