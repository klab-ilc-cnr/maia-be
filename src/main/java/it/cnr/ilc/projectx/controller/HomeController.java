package it.cnr.ilc.projectx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    protected Environment environment;

    @GetMapping("")
    public String index() {
        String name = environment.getProperty("application.name");
        String version = environment.getProperty("application.version");
        return name + " ver. " + version + " is running!";
    }

}
