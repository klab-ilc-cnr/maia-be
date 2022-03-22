package it.cnr.ilc.projectx.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description of ApplicationConfig
 * <p>
 * Created at 21/03/2022 14:20
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@Configuration
@ConfigurationProperties(prefix = "spring.projectx-config")
@Getter
@Setter
public class ApplicationConfig {

    private Keycloak keycloak;

    @Getter
    @Setter
    public static class Keycloak {

        private UserManagerClient userManager;

        @Getter
        @Setter
        public static class UserManagerClient {

            private String clientId;
            private String clientSecret;

        }
    }
}
