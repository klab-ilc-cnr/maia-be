package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.UpdateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.model.Attribute;
import it.cnr.ilc.projectx.model.Language;
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

/**
 * Description of UserService
 * <p>
 * Created at 18/03/2022 16:45
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final RestTemplate restTemplate;

    public List<UserDto> call() {
        HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("authorization", curRequest.getHeader("Authorization"));
        HttpEntity<String> entity = new HttpEntity(headers);
        //Parse the string after getting the response
        ResponseEntity<List<UserDto>> users = restTemplate.exchange("http://localhost:9090/fnape/api/utenti",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserDto>>() {
                });
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
    public UserDto update(UpdateUserDto updateUserDto) {
        checkArgument(updateUserDto != null);
        checkArgument(updateUserDto.getId() != null);
        checkArgument(updateUserDto.getId() > 0);

        UserDto userDto = findById(updateUserDto.getId());

        if (userDto == null) {
            log.error("Cannot find user with ID " + updateUserDto.getId());
            throw new NotFoundException("Cannot find user with ID " + updateUserDto.getId());
        }

        User userUpdatedDto = userRepository.save(mapToEntity(userDto, updateUserDto));

        return mapToDto(userUpdatedDto);
    }

    private User mapToEntity(UserDto userDto, UpdateUserDto updateUserDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        BeanUtils.copyProperties(updateUserDto, user);

        user.setRoles(EnumSet.of(updateUserDto.getRole()));
        user.setUpdated(LocalDateTime.now());
        user.setUpdatedBy(UserUtils.getLoggedUserId());
//        user.setActive(updateUserDto.isActive());
//        user.setName(updateUserDto.getName());
//        user.setSurname(updateUserDto.getSurname());
//        user.getAttributes().setLang(updateUserDto.getLanguages());
        Optional.ofNullable(user.getAttributes())
                .ifPresentOrElse(attribute -> attribute.setLang(updateUserDto.getLanguages()),
                        () -> {
                            user.setAttributes(new Attribute());
                            user.getAttributes().setLang(updateUserDto.getLanguages());
                        });
        return user;
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
//        dto.setLanguages(user.getAttributes().getLang());
        Optional.ofNullable(user.getAttributes())
                .ifPresent(attribute -> dto.setLanguages(attribute.getLang()));
        return dto;
    }

    public CreateUserDto mapToCreateUserDto(User user) {
        CreateUserDto dto = new CreateUserDto();
        BeanUtils.copyProperties(user, dto);
        dto.setRole(user.getRoles().stream().findFirst().get());
//        dto.setLanguages(user.getAttributes().getLang());
        Optional.ofNullable(user.getAttributes())
                .ifPresent(attribute -> dto.setLanguages(attribute.getLang()));
        return dto;
    }

    public User mapToEntity(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setRoles(EnumSet.of(dto.getRole()));
//        user.getAttributes().setLang(dto.getLanguages());
        Optional.ofNullable(user.getAttributes())
                .ifPresentOrElse(attribute -> attribute.setLang(dto.getLanguages()),
                        () -> {
                            user.setAttributes(new Attribute());
                            user.getAttributes().setLang(dto.getLanguages());
                        });
        return user;
    }

    public User mapToEntity(CreateUserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setRoles(EnumSet.of(dto.getRole()));
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
//        user.getAttributes().setLang(dto.getLanguages());
        Optional.ofNullable(user.getAttributes())
                .ifPresentOrElse(attribute -> attribute.setLang(dto.getLanguages()),
                        () -> {
                            user.setAttributes(new Attribute());
                            user.getAttributes().setLang(dto.getLanguages());
                        });

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

    @Transactional(readOnly = true)
    public UserDto findById(@NotBlank Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return null;
        }

        return mapToDto(user.get());
    }
}
