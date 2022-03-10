package it.cnr.ilc.projectx.config;

import lombok.Getter;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description of KeycloakConfig
 * <p>
 * Created at 19/11/2020 09:59
 * Author Elisa Croci (EC) - <e.croci@xeel.tech>
 */
@Configuration
@Getter
public class KeycloakConfig {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realmName;

    @Value("${keycloak.resource}")
    private String clientId;

    @Bean
    KeycloakSpringBootConfigResolver configResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
