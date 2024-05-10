package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.model.Role;
import it.cnr.ilc.maia.utils.UserUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/system")
public class SystemController {

    @Autowired
    protected Environment environment;

    @GetMapping("info")
    public Map<String, Object> info() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("application.name", environment.getProperty("application.name"));
        info.put("application.version", environment.getProperty("application.version"));
        return info;
    }

    @GetMapping("environment")
    public Map<String, Map<String, String>> environment() throws Exception {
        if (!UserUtils.getLoggedUser().getRole().equals(Role.ADMINISTRATOR)) {
            throw new Exception("not allowed");
        }
        Map<String, Map<String, String>> map = new HashMap();
        for (PropertySource propertySource : ((AbstractEnvironment) environment).getPropertySources()) {
            if (propertySource instanceof MapPropertySource mapPropertySource) {
                map.put(mapPropertySource.getName(),
                        mapPropertySource.getSource().entrySet().stream()
                                .filter(e -> !e.getKey().toLowerCase().contains("password"))
                                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().toString())));
            }
        }
        return map;
    }

}
