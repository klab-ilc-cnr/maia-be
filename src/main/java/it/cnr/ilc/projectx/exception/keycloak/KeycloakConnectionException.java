package it.cnr.ilc.projectx.exception.keycloak;

public class KeycloakConnectionException extends RuntimeException {

    public KeycloakConnectionException(String message) {
        super(message);
    }

    public KeycloakConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
