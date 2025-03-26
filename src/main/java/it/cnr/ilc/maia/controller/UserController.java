package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.CreateUserDto;
import it.cnr.ilc.maia.dto.UpdatePasswordDto;
import it.cnr.ilc.maia.dto.UpdateUserDto;
import it.cnr.ilc.maia.dto.UserDto;
import it.cnr.ilc.maia.mediator.Mediator;
import it.cnr.ilc.maia.model.Role;
import it.cnr.ilc.maia.request.CreateUserRequest;
import it.cnr.ilc.maia.request.DeleteUserRequest;
import it.cnr.ilc.maia.request.UpdateUserRequest;
import it.cnr.ilc.maia.service.UserService;
import it.cnr.ilc.maia.utils.UserUtils;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/users")
@Slf4j
public class UserController {

    @NonNull
    private final Mediator mediator;

    @NonNull
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<List<String>> getRoles() {
        return ResponseEntity.ok(userService.getRoles());
    }

    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser() {
        UserDto user = UserUtils.getLoggedUser();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/password")
    public ResponseEntity<UserDto> setPassword(@RequestBody @NotNull UpdatePasswordDto updatePasswordDto) throws Exception {
        if (updatePasswordDto.getId() == null) {
            updatePasswordDto.setId(UserUtils.getLoggedUserId());
        }
        if (!UserUtils.getLoggedUserId().equals(updatePasswordDto.getId())
                && !UserUtils.getLoggedUser().getRole().equals(Role.ADMINISTRATOR)) {
            throw new Exception("not allowed");
        }
        UserDto usertDto = userService.updatePassword(updatePasswordDto);
        return ResponseEntity.ok(usertDto);
    }

    @PostMapping
    public ResponseEntity<CreateUserDto> addUser(@Valid @RequestBody @NotNull CreateUserDto createUserDto) throws Exception {
        XResult<CreateUserDto> response = mediator.sendXResult(new CreateUserRequest(createUserDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        CreateUserDto responseUserDto = response.getPayload();
        return ResponseEntity.ok(responseUserDto);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto userDto) throws Exception {
        XResult<UserDto> response = mediator.sendXResult(new UpdateUserRequest(userDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        UserDto responseUserDto = response.getPayload();
        return ResponseEntity.ok(responseUserDto);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable @NotNull Long userId) throws Exception {
        XResult<Long> response = mediator.sendXResult(new DeleteUserRequest(userId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }
}
