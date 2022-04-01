package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.dto.UserUpdatedDto;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description of UserService
 * <p>
 * Created at 18/03/2022 16:45
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final RestTemplate restTemplate;

    public List<UserDto> call(){
        HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("authorization", curRequest.getHeader("Authorization"));
        HttpEntity<String> entity = new HttpEntity(headers);
        //Parse the string after getting the response
        ResponseEntity<List<UserDto>> users = restTemplate.exchange("http://localhost:9090/fnape/api/utenti",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserDto>>() {});
        return users.getBody();
    }


    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return mapToDtos(users);
    }

    @Transactional(readOnly = true)
    public UserDto getUser(@NotNull Long id) {
        User user = userRepository.getById(id);
        return mapToDto(user);
    }

    @Transactional
    public CreateUserDto add(CreateUserDto userDto) {
        User user = userRepository.save(mapToEntity(userDto));
        return mapToCreateUserDto(user);
    }

    @Transactional
    public UserUpdatedDto update(UserDto userDto) {
        UserUpdatedDto userUpdatedDto = mapToUpdateUserDto(userDto);
        userRepository.save(mapToEntity(userDto));

        return userUpdatedDto;
    }

    private List<UserDto> mapToDtos(List<User> users) {
        if (users == null) {
            return Collections.emptyList();
        } else {
            return users
                    .stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }
    }

    public UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        dto.setRole(user.getRoles().stream().findFirst().get());
        return dto;
    }

    public CreateUserDto mapToCreateUserDto(User user) {
        CreateUserDto dto = new CreateUserDto();
        BeanUtils.copyProperties(user, dto);
        dto.setRole(user.getRoles().stream().findFirst().get());
        return dto;
    }

    public User mapToEntity(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setRoles(EnumSet.of(dto.getRole()));
        return user;
    }

    public User mapToEntity(CreateUserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setRoles(EnumSet.of(dto.getRole()));
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        return user;
    }

    @Transactional(readOnly = true)
    public UserDto getUserByEmail(@NotBlank String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }

        return mapToDto(user.get());
    }

    public UserUpdatedDto mapToUpdateUserDto(UserDto user) {
        UserUpdatedDto updatedUserDto = new UserUpdatedDto();
        BeanUtils.copyProperties(user, updatedUserDto);
        updatedUserDto.setUpdated(LocalDateTime.now());
        return updatedUserDto;
    }
}
