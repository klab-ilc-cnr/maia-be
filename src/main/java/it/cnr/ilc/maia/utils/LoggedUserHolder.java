package it.cnr.ilc.maia.utils;

import it.cnr.ilc.maia.dto.UserDto;

public final class LoggedUserHolder {

    private static final ThreadLocal<UserDto> CONTEXT = new ThreadLocal<>();

    public static void setUser(UserDto user) {
        CONTEXT.set(user);
    }

    public static UserDto getUser() {
        return CONTEXT.get();
    }

    public static void remove() {
        CONTEXT.get();
    }

    private LoggedUserHolder() {
    }

}
