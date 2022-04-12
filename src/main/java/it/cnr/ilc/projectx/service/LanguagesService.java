package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.model.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LanguagesService {

    //TODO recuperarle dal servizio o dalla fonte adeguata
    public List<Language> retrieveAll() {
        return List.of(new Language("Italiano", "it"),
                new Language("Tedesco", "de"),
                new Language("Francese", "fr"),
                new Language("Ungherese", "hu"));
    }
}
