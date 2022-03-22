package it.cnr.ilc.projectx.exception.keycloak;

public class KeycloakTooManyEntitiesException extends RuntimeException {

    public KeycloakTooManyEntitiesException(String message) {
        super(message);
    }

    public KeycloakTooManyEntitiesException(String message, Throwable cause) {
        super(message, cause);
    }

}
