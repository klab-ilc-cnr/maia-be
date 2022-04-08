package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.dto.UpdateUserDto;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.repository.UserRepository;
import it.cnr.ilc.projectx.utils.UserUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        UserDto userDto = getUserByEmail(updateUserDto.getEmail());

        if (userDto == null) {
            log.error("Cannot find user for email " + updateUserDto.getEmail());
            throw new NotFoundException("Cannot find user for email " + updateUserDto.getEmail());
        }

        User userUpdatedDto = userRepository.save(mapToEntity(userDto, updateUserDto));

        return mapToDto(userUpdatedDto);
    }

    private User mapToEntity(UserDto userDto, UpdateUserDto updateUserDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        user.setRoles(EnumSet.of(updateUserDto.getRole()));
        user.setUpdated(LocalDateTime.now());
        user.setActive(updateUserDto.isActive());
        user.setName(updateUserDto.getName());
        user.setSurname(updateUserDto.getSurname());

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

//    public User mapToEntity(CreateUserDto dto) {
//        User user = new User();
//        BeanUtils.copyProperties(dto, user);
//        user.setRoles(EnumSet.of(dto.getRole()));
//        user.setCreated(LocalDateTime.now());
//        user.setUpdated(LocalDateTime.now());
//        return user;
//    }

    public User mapToEntity(CreateUserDto dto) {
        User user = new User();
        user.setActive(dto.isActive());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setCreatedBy(UserUtils.getLoggedUserId());
        user.setUpdatedBy(UserUtils.getLoggedUserId());
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
}
