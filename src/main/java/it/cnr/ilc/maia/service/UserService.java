package it.cnr.ilc.maia.service;

import it.cnr.ilc.maia.dto.CreateUserDto;
import it.cnr.ilc.maia.dto.UpdateUserDto;
import it.cnr.ilc.maia.dto.UserDto;
import it.cnr.ilc.maia.model.Attribute;
import it.cnr.ilc.maia.model.User;
import it.cnr.ilc.maia.repository.UserRepository;
import it.cnr.ilc.maia.utils.UserUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static com.google.common.base.Preconditions.checkArgument;
import it.cnr.ilc.maia.dto.UpdatePasswordDto;
import it.cnr.ilc.maia.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Service
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
    public List<String> getRoles() {
        return Arrays.stream(Role.values())
                .map(r -> r.toString())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getUser(@NotNull Long id) {
        User user = userRepository.getReferenceById(id);
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
        checkArgument(updateUserDto.getId() > 0);
        Optional<User> optional = userRepository.findById(updateUserDto.getId());
        if (optional.isEmpty()) {
            throw new NotFoundException("Cannot find user with ID " + updateUserDto.getId());
        }
        User user = optional.get();
        UserDto userDto = mapToDto(user);
        user = userRepository.save(mapToEntity(userDto, updateUserDto, user.getPassword()));
        return mapToDto(user);
    }

    public UserDto updatePassword(UpdatePasswordDto updatePasswordDto) throws Exception {
        Optional<User> optional = userRepository.findById(updatePasswordDto.getId());
        if (optional.isEmpty()) {
            throw new NotFoundException("Cannot find user with ID " + updatePasswordDto.getId());
        }
        User user = optional.get();
        if (UserUtils.getLoggedUserId().equals(user.getId())
                && !updatePasswordDto.getCurrentPassword().equals(user.getPassword())) {
            throw new Exception("Wrong password");
        }
        user.setPassword(updatePasswordDto.getNewPassword());
        userRepository.save(user);
        return mapToDto(user);
    }

    private User mapToEntity(UserDto userDto, UpdateUserDto updateUserDto, String password) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        BeanUtils.copyProperties(updateUserDto, user);
        user.setPassword(password);
        user.setRoles(EnumSet.of(updateUserDto.getRole()));
        user.setUpdated(LocalDateTime.now());
        user.setUpdatedBy(UserUtils.getLoggedUserId());
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
        Optional.ofNullable(user.getAttributes())
                .ifPresent(attribute -> dto.setLanguages(attribute.getLang()));
        return dto;
    }

    public CreateUserDto mapToCreateUserDto(User user) {
        CreateUserDto dto = new CreateUserDto();
        BeanUtils.copyProperties(user, dto);
        dto.setRole(user.getRoles().stream().findFirst().get());
        Optional.ofNullable(user.getAttributes())
                .ifPresent(attribute -> dto.setLanguages(attribute.getLang()));
        return dto;
    }

    public User mapToEntity(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setRoles(EnumSet.of(dto.getRole()));
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
        BeanUtils.copyProperties(dto, user, "id");
        user.setRoles(EnumSet.of(dto.getRole()));
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
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
    public UserDto getUserByUsername(@NotBlank String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return mapToDto(user.get());
    }

    @Transactional(readOnly = true)
    public UserDto getUserByUsernameAndPassword(@NotBlank String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
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
