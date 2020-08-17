package com.revature.p0.user;

import com.revature.p0.models.AppUser;

public class CurrentUser {
    public static AppUser currentUser;

    public CurrentUser() {
    }

    public static AppUser getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(AppUser currentUser) {
        CurrentUser.currentUser = currentUser;
    }
}
