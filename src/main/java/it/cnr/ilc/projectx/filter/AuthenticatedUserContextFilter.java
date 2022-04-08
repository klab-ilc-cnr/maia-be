package it.cnr.ilc.projectx.filter;

import it.cnr.ilc.projectx.errors.ApiError;
import it.cnr.ilc.projectx.errors.ApiSubError;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.repository.UserRepository;
import it.cnr.ilc.projectx.utils.LoggedUserHolder;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static it.cnr.ilc.projectx.filter.FilterErrors.ErrorCode.ACCOUNT_NOT_FOUND;
import static it.cnr.ilc.projectx.filter.FilterErrors.fromErrorCode;
import static it.cnr.ilc.projectx.filter.FilterErrors.setError;

@Slf4j
public class AuthenticatedUserContextFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public AuthenticatedUserContextFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = getAuthentication(req);
        if (authentication == null) {
            log.info(String.format("%s - NO AUTHENTICATION FOUND", req.getRequestURI()));
            filterChain.doFilter(req, res);
            return;
        }

        if (req.getRequestURI().contains("/auth/")) {
            filterChain.doFilter(req, res);
            return;
        }

        AccessToken token = getAccessToken(req, authentication);
        if (token == null) {
            log.error(String.format("%s - No access token provided!", req.getRequestURI()));
            throw new AuthenticationCredentialsNotFoundException("No access token");
        }

        String email = token.getEmail();
        if (email == null) {
            log.error(String.format("%s - Access token for user %s do not contain email", req.getRequestURI(), token.getSubject()));
            throw new AuthenticationCredentialsNotFoundException("No email claim");
        }

        Optional<User> maybe = userRepository.findByEmail(email);
        if (maybe.isEmpty()) {
            ApiError<ApiSubError> error = fromErrorCode(
                    HttpStatus.FORBIDDEN,
                    ACCOUNT_NOT_FOUND,
                    String.format("No user with email <%s>", email)
            );
            setError(req, res, error);
            return;
        }

        User user = maybe.get();

        try {
            LoggedUserHolder.setUser(user);
            filterChain.doFilter(req, res);
        } finally {
            LoggedUserHolder.clear();
        }
    }

    private Authentication getAuthentication(HttpServletRequest req) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            log.debug(String.format("%s - No SecurityContext available", req.getRequestURI()));
            return null;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            log.debug(String.format("%s - No Authentication available", req.getRequestURI()));
            return null;
        }

        return authentication;
    }

    private UsernamePasswordAuthenticationToken getUPToken(HttpServletRequest req, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            log.debug(String.format("%s - No principal", req.getRequestURI()));
            return null;
        }

        if (!(principal instanceof org.springframework.security.core.userdetails.User)) {
            log.debug(String.format("%s - Principal not coming from UsernamePasswordAuthentication", req.getRequestURI()));
            return null;
        }

        log.debug(String.format("%s - Principal from outside", req.getRequestURI()));
        return (UsernamePasswordAuthenticationToken) authentication;
    }

    private AccessToken getAccessToken(HttpServletRequest req, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            log.debug(String.format("%s - No principal", req.getRequestURI()));
            return null;
        }

        if (!(principal instanceof KeycloakPrincipal)) {
            log.debug(String.format("%s - Principal not from Keycloak", req.getRequestURI()));
            return null;
        }

        return ((KeycloakPrincipal<RefreshableKeycloakSecurityContext>) principal)
                .getKeycloakSecurityContext()
                .getToken();
    }

}
