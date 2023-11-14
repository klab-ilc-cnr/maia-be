package it.cnr.ilc.projectx.utils;

import it.cnr.ilc.projectx.dto.UserDto;

public final class UserUtils {

    public static Long getLoggedUserId() {
        UserDto user = getLoggedUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public static UserDto getLoggedUser() {
        return LoggedUserHolder.getUser();
    }
}
