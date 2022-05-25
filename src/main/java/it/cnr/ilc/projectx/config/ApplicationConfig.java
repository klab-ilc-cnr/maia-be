package it.cnr.ilc.projectx.config;

import it.cnr.ilc.projectx.utils.SecurityAuditorAware;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * Description of ApplicationConfig
 * <p>
 * Created at 21/03/2022 14:20
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
@ConfigurationProperties(prefix = "spring.projectx-config")
@Getter
@Setter
public class ApplicationConfig {

    private Keycloak keycloak;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }

    @Bean
    public AuditorAware<Long> auditorAware(){
        return new SecurityAuditorAware();
    }

    @Getter
    @Setter
    public static class Keycloak {

        private UserManagerClient userManager;

        @Getter
        @Setter
        public static class UserManagerClient {

            private String clientId;
            private String clientSecret;
            private String defaultRoleName;

        }
    }
}
