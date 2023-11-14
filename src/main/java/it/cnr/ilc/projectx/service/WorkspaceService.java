package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.Workspace;
import it.cnr.ilc.projectx.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.NotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import lombok.NonNull;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    @NonNull
    private final WorkspaceRepository workspaceRepository;

    @Transactional(readOnly = true)
    public List<WorkspaceChoiceDto> retrieveAll() {
        List<Workspace> result = workspaceRepository.findAll();
        return result.stream()
                .map(workspace -> mapToWorkspaceChoiceDto(workspace))
                .collect(Collectors.toList());
    }

    private Timestamp convertToTimestamp(String stringDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(stringDate);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            // this generic but you can control another types of exception
            // look the origin of excption
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<TextChoiceDto> getTextChoiceList() {
        //TODO recuperare i dati correttamente

        List<TextChoiceDto> result = new LinkedList<>();

        TextChoiceDto text1 = new TextChoiceDto();
        text1.setId(1l);
        text1.setTitle("provaTitolo");
        text1.setCreatedBy("amministratore@gmail.com");
        text1.setStatus("");
        text1.setUpdatedOn("21/04/2022");
        result.add(text1);

        TextChoiceDto text2 = new TextChoiceDto();
        text2.setId(2l);
        text2.setTitle("provaTitolo2");
        text2.setCreatedBy("utente@gmail.com");
        text2.setStatus("");
        text2.setUpdatedOn("13/05/2022");
        result.add(text2);

        TextChoiceDto text3 = new TextChoiceDto();
        text3.setId(3l);
        text3.setTitle("provaTitolo3");
        text3.setCreatedBy("utente2@gmail.com");
        text3.setStatus("");
        text3.setUpdatedOn("16/03/2022");
        result.add(text3);

        return result;
    }

    /*    @Transactional(readOnly = true)
    public TextTileDto getText(Long textId) {
        //TODO recuperare i dati correttamente

        TextTileDto result = new TextTileDto();
        if (textId == 1l) {
            result.setText("Testo 1 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        } else if (textId == 2l) {
            result.setText("Testo 2 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        } else if (textId == 3l) {
            result.setText("Testo 3 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        }
        return result;
    }*/
    public WorkspaceDto getWorkspace(Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(NotFoundException::new);

        WorkspaceDto workspaceDto = mapToWorkspaceDto(workspace);

        /*        int index = 1;
        for (TileDto tile : workspaceDto.getTiles()) {
            TextTileDto textTileDto = new TextTileDto();
            //FIXME CARICARE I DATI CORRETTI PRENDENDOLI DALLE API
            switch (index) {
                case 1:
                    textTileDto.setId(1l);
                    tile.setContent(textTileDto);
                    break;
                case 2:
                    textTileDto.setId(2l);
                    tile.setContent(textTileDto);
                    break;
                case 3:
                    textTileDto.setId(3l);
                    tile.setContent(textTileDto);
                    break;
            }
            index++;
        }
        ;*/
        return workspaceDto;
    }

    public String getWorkspaceName(Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(NotFoundException::new);
        String workspaceName = workspace.getName();
        return workspaceName;
    }

    @Transactional
    public WorkspaceChoiceDto add(CreateWorkspaceDto workspaceChoiceDto) {
        Workspace workspace = workspaceRepository.save(mapToEntity(workspaceChoiceDto));
        return mapToWorkspaceChoiceDto(workspace);
    }

    @Transactional
    public void delete(Workspace workspace) {
        workspaceRepository.delete(workspace);
    }

    public Workspace retrieveWorkspace(Long workspaceId) {
        Optional<Workspace> maybe = workspaceRepository.findById(workspaceId);
        return maybe.get();
    }

    @Transactional
    public WorkspaceChoiceDto update(UpdateWorkspaceChoiceDto updateWorkspaceChoiceDto) {
        checkArgument(updateWorkspaceChoiceDto != null);
        checkArgument(updateWorkspaceChoiceDto.getId() != null);
        checkArgument(updateWorkspaceChoiceDto.getName() != null);

        Workspace tobeUpdated = retrieveWorkspace(updateWorkspaceChoiceDto.getId());

        if (tobeUpdated == null) {
            throw new NotFoundException("Cannot find workspace with ID " + updateWorkspaceChoiceDto.getId());
        }

        workspaceRepository.save(mapToEntity(tobeUpdated, updateWorkspaceChoiceDto));

        Workspace workspace = retrieveWorkspace(updateWorkspaceChoiceDto.getId());

        return mapToWorkspaceChoiceDto(workspace);
    }

    private Workspace mapToEntity(CreateWorkspaceDto createWorkspaceDto) {
        Workspace workspace = new Workspace();
        BeanUtils.copyProperties(createWorkspaceDto, workspace);
        return workspace;
    }

    private Workspace mapToEntity(Workspace tobeUpdated, UpdateWorkspaceChoiceDto updateWorkspaceChoiceDto) {
        Workspace workspace = new Workspace();
        BeanUtils.copyProperties(tobeUpdated, workspace);
        BeanUtils.copyProperties(updateWorkspaceChoiceDto, workspace);
        return workspace;
    }

    private WorkspaceChoiceDto mapToWorkspaceChoiceDto(Workspace workspace) {
        WorkspaceChoiceDto workspaceChoiceDto = new WorkspaceChoiceDto();
        BeanUtils.copyProperties(workspace, workspaceChoiceDto);
        return workspaceChoiceDto;
    }

    private WorkspaceDto mapToWorkspaceDto(Workspace workspace) {
        WorkspaceDto workspaceDto = new WorkspaceDto();
        workspaceDto.setId(workspace.getId());
        workspaceDto.setLayout(workspace.getLayout());
        List<TileDto> tilesDto = workspace.getTiles().stream().map(tile -> TileService.mapToTileDto(tile)).collect(Collectors.toList());
        workspaceDto.setTiles(tilesDto);
        return workspaceDto;
    }
}
