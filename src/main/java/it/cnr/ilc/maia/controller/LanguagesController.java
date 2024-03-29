package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.model.Language;
import it.cnr.ilc.maia.service.LanguagesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/languages")

public class LanguagesController {

    @NonNull
    private final LanguagesService languagesService;

    @GetMapping
    public ResponseEntity<List<Language>> retrieveAll() {
        return ResponseEntity.ok(languagesService.retrieveAll());
    }
}
