package it.cnr.ilc.projectx.exception.keycloak;

public class KeycloakEntityAlreadyExistsException extends RuntimeException {

    public KeycloakEntityAlreadyExistsException(String message) {
        super(message);
    }

    public KeycloakEntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
