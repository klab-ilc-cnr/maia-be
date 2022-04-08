package it.cnr.ilc.projectx.utils;

import it.cnr.ilc.projectx.model.User;

import static com.google.common.base.Preconditions.checkNotNull;

public final class UserUtils {

    public static Long getLoggedUserId() {
        User user = getLoggedUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public static User getLoggedUser() {
        return LoggedUserHolder.getUser();
    }
}
