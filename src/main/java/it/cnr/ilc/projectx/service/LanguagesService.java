package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.LanguageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LanguagesService {

    //TODO recuperarle dal servizio o dalla fonte adeguata
    public List<LanguageDto> retrieveAll() {
        return List.of(new LanguageDto("Italiano", "it"),
                new LanguageDto("Tedesco", "de"),
                new LanguageDto("Francese", "fr"),
                new LanguageDto("Ungherese", "hu"));
    }
}
