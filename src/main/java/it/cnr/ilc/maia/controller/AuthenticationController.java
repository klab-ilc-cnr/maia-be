package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.AuthenticationDto;
import it.cnr.ilc.maia.dto.UserDto;
import it.cnr.ilc.maia.service.AuthenticationService;
import it.cnr.ilc.maia.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/authentication")
@Slf4j
public class AuthenticationController {

    @NonNull
    private final UserService userService;
    @NonNull
    private final AuthenticationService authenticationService;

    @PostMapping("authenticate")
    public ResponseEntity<String> create(@RequestBody @NotNull AuthenticationDto authenticationDto) throws Exception {
        UserDto userDto = userService.getUserByUsernameAndPassword(authenticationDto.getUsername(), authenticationDto.getPassword());
        if (userDto == null) {
            return new ResponseEntity("authentication failed", HttpStatus.UNAUTHORIZED);

        } else {
            String jwtToken = authenticationService.createJwt(userDto);
            return ResponseEntity.ok(jwtToken);
        }
    }

    @PostMapping("renew")
    public ResponseEntity<String> renew(@RequestBody @NotNull String jwtToken) throws Exception {
        jwtToken = authenticationService.renewJwt(jwtToken);
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("publicKey")
    public ResponseEntity<String> getPublicKey() throws Exception {
        String publicKey = authenticationService.getPublicKey();
        return ResponseEntity.ok(publicKey);
    }

}
