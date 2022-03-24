package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.CreateUser;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws Exception {
        XResult<UserDto> response = mediator.sendXResult(new CreateUser(userDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        UserDto responseUserDto = response.getPayload();
        return ResponseEntity.ok(responseUserDto);
    }
}
