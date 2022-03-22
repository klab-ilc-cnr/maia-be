package it.cnr.ilc.projectx.exception.keycloak;

public class KeycloakEntityNotFoundException extends RuntimeException {

    public KeycloakEntityNotFoundException(String message) {
        super(message);
    }

    public KeycloakEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
