package it.cnr.ilc.projectx.config;

import it.cnr.ilc.projectx.filter.AuthenticatedUserContextFilter;
import it.cnr.ilc.projectx.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.Filter;

/**
 * Description of SecurityConfig
 * <p>
 * Created at 10/03/2022 14:20
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
//@Configuration
//@EnableWebSecurity
//@KeycloakConfiguration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter
{
    @NonNull
    private final UserRepository userRepository;

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
    }

    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http.cors()
                .and()
                .authorizeRequests()
//                .antMatchers("/api/users").authenticated()
//                .antMatchers("/api/users").hasAnyRole("Amministratore")
//                .anyRequest().permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(authenticatedUserContextFilter(), FilterSecurityInterceptor.class)
                .csrf().disable();
    }

    @Bean
    public AuthenticatedUserContextFilter authenticatedUserContextFilter() {
        return new AuthenticatedUserContextFilter(userRepository);
    }
}

//        http.cors()
//                .and()
//                    .authorizeRequests()
//                        .antMatchers(HttpMethod.GET, "/api/users").authenticated()
//                        .antMatchers(HttpMethod.POST, "/api/users").authenticated()
//                        .anyRequest()
//                            .authenticated()
//                .and()
//                    .oauth2ResourceServer()
//                        .jwt();


