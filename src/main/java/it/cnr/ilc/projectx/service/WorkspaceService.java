package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.TextDto;
import it.cnr.ilc.projectx.dto.UpdateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.model.Attribute;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.repository.UserRepository;
import it.cnr.ilc.projectx.utils.UserUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<TextDto> getTexts() {
        //TODO recuperare i dati correttamente

        List<TextDto> result = new LinkedList<>();

        TextDto text1 = new TextDto();
        text1.setId(1l);
        text1.setTitle("provaTitolo");
        text1.setCreatedBy("amministratore@gmail.com");
        text1.setStatus("Aperto");
        text1.setUpdatedOn("21/04/2022");
        result.add(text1);

        TextDto text2 = new TextDto();
        text2.setId(2l);
        text2.setTitle("provaTitolo2");
        text2.setCreatedBy("utente@gmail.com");
        text2.setStatus("");
        text2.setUpdatedOn("13/05/2022");
        result.add(text2);

        TextDto text3 = new TextDto();
        text3.setId(3l);
        text3.setTitle("provaTitolo3");
        text3.setCreatedBy("utente2@gmail.com");
        text3.setStatus("Aperto");
        text3.setUpdatedOn("16/03/2022");
        result.add(text3);

        return result;
    }
}
