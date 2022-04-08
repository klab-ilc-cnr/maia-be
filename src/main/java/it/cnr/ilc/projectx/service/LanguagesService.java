package it.cnr.ilc.projectx.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LanguagesService {

    //TODO recuperarle dal servizio o dalla fonte adeguata
    public List<String> retrieveAll() {
        return List.of("it","de","fr","hu");
    }
}
