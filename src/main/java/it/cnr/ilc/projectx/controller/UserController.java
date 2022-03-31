package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.UpdateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.CreateUserRequest;
import it.cnr.ilc.projectx.request.UpdateUserRequest;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description of UserController
 * <p>
 * Created at 08/03/2022 15:25
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/users")
public class UserController {
    @NonNull
    private final Mediator mediator;

    @NonNull
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE, T(it.cnr.ilc.projectx.model.Role).SUPERVISORE)")
    public ResponseEntity<UserDto> getUser(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<CreateUserDto> addUser(@RequestBody CreateUserDto createUserDto) throws Exception {
        XResult<CreateUserDto> response = mediator.sendXResult(new CreateUserRequest(createUserDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        CreateUserDto responseUserDto = response.getPayload();
        return ResponseEntity.ok(responseUserDto);
    }

    @PostMapping("/updateUser")
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto userDto) throws Exception {
        XResult<UserDto> response = mediator.sendXResult(new UpdateUserRequest(userDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        UserDto responseUserDto = response.getPayload();
        return ResponseEntity.ok(responseUserDto);
    }
}
