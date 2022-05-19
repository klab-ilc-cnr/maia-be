package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.TextChoiceDto;
import it.cnr.ilc.projectx.dto.TextTileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkspaceService {

//    @NonNull
//    private final WorkspaceRepository workspaceRepository;

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
            result.setContent("Testo 1 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        } else if (textId == 2l) {
            result.setContent("Testo 2 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        } else if (textId == 3l) {
            result.setContent("Testo 3 \n Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        }
        return result;
    }
}
