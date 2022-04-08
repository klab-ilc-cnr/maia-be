package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.service.LanguagesService;
import it.cnr.ilc.projectx.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/languages")

public class LanguagesController {

    @NonNull
    private final LanguagesService languagesService;

    @GetMapping
    @PreAuthorize("hasAnyRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<List<String>> retrieveAll() {
        return ResponseEntity.ok(languagesService.retrieveAll());
    }
}
