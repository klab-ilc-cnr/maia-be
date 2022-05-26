package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.Tile;
import it.cnr.ilc.projectx.model.Workspace;
import it.cnr.ilc.projectx.repository.WorkspaceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkspaceService {

    @NonNull
    private final WorkspaceRepository workspaceRepository;

//    @NonNull
//    private final RestTemplate restTemplate;

//    public List<UserDto> call() {
//        HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("authorization", curRequest.getHeader("Authorization"));
//        HttpEntity<String> entity = new HttpEntity(headers);
//        //Parse the string after getting the response
//        ResponseEntity<List<UserDto>> users = restTemplate.exchange("http://localhost:9090/fnape/api/utenti",
//                HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserDto>>() {
//                });
//        return users.getBody();
//    }

    @Transactional(readOnly = true)
    public List<WorkspaceChoiceDto> retrieveAll() {
/*        List<WorkspaceChoiceDto> result = new LinkedList<>();

        WorkspaceChoiceDto ws1 = new WorkspaceChoiceDto();
        ws1.setId(1l);
        ws1.setUpdated(convertToTimestamp("2021-12-25 00:00:00.000"));
        ws1.setNote("Testo 1 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.");
        ws1.setName("Workspace1");
        result.add(ws1);

        WorkspaceChoiceDto ws2 = new WorkspaceChoiceDto();
        ws2.setId(2l);
        ws2.setUpdated(convertToTimestamp("2022-03-13 00:00:00.000"));
        ws2.setNote("Testo 2 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.");
        ws2.setName("Workspace2");
        result.add(ws2);

        WorkspaceChoiceDto ws3 = new WorkspaceChoiceDto();
        ws3.setId(3l);
        ws3.setUpdated(convertToTimestamp("2022-04-07 00:00:00.000"));
        ws3.setNote("Testo 3 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.");
        ws3.setName("Workspace3");
        result.add(ws3);

        return result;*/

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
        } catch (Exception e) { //this generic but you can control another types of exception
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
        text1.setStatus("Aperto");
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
        text3.setStatus("Aperto");
        text3.setUpdatedOn("16/03/2022");
        result.add(text3);

        return result;
    }

    @Transactional(readOnly = true)
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
    }

    public List<TileDto> getTiles(Long workspaceId) {
        //TODO
        return null;
    }

    public WorkspaceChoiceDto add(CreateWorkspaceDto workspaceChoiceDto) {
        Workspace workspace = workspaceRepository.save(mapToEntity(workspaceChoiceDto));
        return mapToWorkspaceChoiceDto(workspace);
    }

    public void delete(Workspace workspace) {
        workspaceRepository.delete(workspace);
    }

    public Workspace retrieveWorkspace(Long workspaceId) {
        Optional<Workspace> maybe = workspaceRepository.findById(workspaceId);
        return maybe.get();
    }

    public WorkspaceChoiceDto update(UpdateWorkspaceDto updateWorkspaceDto) {
        checkArgument(updateWorkspaceDto != null);
        checkArgument(updateWorkspaceDto.getId() != null);
        checkArgument(updateWorkspaceDto.getName() != null);

        Workspace tobeUpdated = retrieveWorkspace(updateWorkspaceDto.getId());

        if (tobeUpdated == null) {
            log.error("Cannot find workspace with ID " + updateWorkspaceDto.getId());
            throw new NotFoundException("Cannot find workspace with ID " + updateWorkspaceDto.getId());
        }

        workspaceRepository.save(mapToEntity(tobeUpdated, updateWorkspaceDto));

        Workspace workspace = retrieveWorkspace(updateWorkspaceDto.getId());

        return mapToWorkspaceChoiceDto(workspace);
    }

    private Workspace mapToEntity(CreateWorkspaceDto createWorkspaceDto) {
        Workspace workspace = new Workspace();
        BeanUtils.copyProperties(createWorkspaceDto, workspace);
        return workspace;
    }

    private Workspace mapToEntity(Workspace tobeUpdated, UpdateWorkspaceDto updateWorkspaceDto) {
        Workspace workspace = new Workspace();
        BeanUtils.copyProperties(tobeUpdated, workspace);
        BeanUtils.copyProperties(updateWorkspaceDto, workspace);
        return workspace;
    }

    private WorkspaceChoiceDto mapToWorkspaceChoiceDto(Workspace workspace) {
        WorkspaceChoiceDto workspaceChoiceDto = new WorkspaceChoiceDto();
        BeanUtils.copyProperties(workspace, workspaceChoiceDto);
        return workspaceChoiceDto;
    }

    @Transactional
    public void savePanelLayout(Long workspaceId, String layout) {
        Optional<Workspace> maybe = workspaceRepository.findById(workspaceId);
        Workspace workspace = maybe.get();
        workspace.setLayout(layout);
        workspaceRepository.save(workspace);
    }
}
