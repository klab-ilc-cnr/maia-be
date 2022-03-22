package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description of UserService
 * <p>
 * Created at 18/03/2022 16:45
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return mapToDtos(users);
    }

    public UserDto save(UserDto userDto) {
        User user = userRepository.save(mapToEntity(userDto));
        return mapToDto(user);
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
        return dto;
    }

    public User mapToEntity(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}