package it.cnr.ilc.projectx.utils;

import it.cnr.ilc.projectx.model.User;

public final class LoggedUserHolder {

    private static final ThreadLocal<User> CONTEXT = new ThreadLocal<>();

    public static void setUser(User user) {
        CONTEXT.set(user);
    }

    public static User getUser() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }

    private LoggedUserHolder() {
    }

}
