package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.UpdatePasswordDto;
import it.cnr.ilc.projectx.dto.UpdateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.model.Role;
import it.cnr.ilc.projectx.request.CreateUserRequest;
import it.cnr.ilc.projectx.request.UpdateUserRequest;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.utils.UserUtils;
import it.cnr.ilc.projectx.xresults.XResult;
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
                && !UserUtils.getLoggedUser().getRole().equals(Role.AMMINISTRATORE)) {
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
}
