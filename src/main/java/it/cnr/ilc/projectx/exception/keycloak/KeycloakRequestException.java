package it.cnr.ilc.projectx.exception.keycloak;

public class KeycloakRequestException extends RuntimeException {

    public KeycloakRequestException(String message) {
        super(message);
    }

    public KeycloakRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
