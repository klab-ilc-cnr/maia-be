package it.cnr.ilc.projectx.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.Tile;
import it.cnr.ilc.projectx.model.TileType;
import it.cnr.ilc.projectx.model.Workspace;
import it.cnr.ilc.projectx.repository.TileRepository;
import it.cnr.ilc.projectx.repository.WorkspaceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class TileService {

    @NonNull
    private final TileRepository tileRepository;

    @NonNull
    private final WorkspaceRepository workspaceRepository;


/*    @Transactional(readOnly = true)
    public List<WorkspaceChoiceDto> retrieveAll() {
        List<Workspace> result = tileRepository.findAll();
        return result.stream()
                .map(workspace -> mapToWorkspaceChoiceDto(workspace))
                .collect(Collectors.toList());
    }*/

    @Transactional
    public void delete(Tile tile) {
        tileRepository.delete(tile);
    }

    private List<Tile> mapToEntity(List<TileDto> tilesDto, Workspace workspace) {
        List<Tile> tiles = new LinkedList<>();
        tilesDto.stream().forEach(tileDto -> tiles.add(mapToEntity(tileDto, workspace)));

        return tiles;
    }

    private Tile mapToEntity(TileDto tileDto, Workspace workspace) {
        Tile tile = new Tile();
        BeanUtils.copyProperties(tileDto, tile);
        tile.setWorkspace(workspace);
        if (tileDto.getType() == TileType.TEXT) {
            ObjectMapper objectMapper = new ObjectMapper();
            TextTileDto textTileDto = objectMapper.convertValue(tileDto.getContent(), TextTileDto.class);
            tile.setContentId(textTileDto.getContentId());
        }
        return tile;
    }

    public static TileDto mapToTileDto(Tile tile) {
        TileDto tileDto = new TileDto();
        BeanUtils.copyProperties(tile, tileDto);
        if (tile.getType() == TileType.TEXT) {
            TextTileDto textTileDto = new TextTileDto();
            textTileDto.setContentId(tile.getContentId());
            tileDto.setContent(textTileDto);
        }
        tileDto.setWorkspaceId(tile.getWorkspace().getId());
        return tileDto;
    }

    @Transactional
    public void saveWorkspace(WorkspaceDto workspaceDto) {
        Workspace workspace = workspaceRepository.findById(workspaceDto.getId()).orElseThrow(EntityNotFoundException::new);

        tileRepository.deleteAll(workspace.getTiles());
        workspace.setTiles(null);//cacelliamo la relazione altrimenti spring non cancella le tiles

        List<Tile> newTiles = mapToEntity(workspaceDto.getTiles(), workspace);

        workspace.setTiles(newTiles);

        workspace.setLayout(workspaceDto.getLayout());

        workspaceRepository.save(workspace);
    }
}
