package it.cnr.ilc.projectx.exception.keycloak;

public class KeycloakServerException extends RuntimeException {

    public KeycloakServerException(String message) {
        super(message);
    }

    public KeycloakServerException(String message, Throwable cause) {
        super(message, cause);
    }

}
