package it.cnr.ilc.projectx.xresults.fail;

public class Fail {
    private String message;

    public Fail(String message) {
        this.message = message;
    }

    public Fail() {
        this("");
    }

    public String getMessage() {
        return message;
    }
}
