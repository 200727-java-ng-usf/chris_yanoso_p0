package com.revature.p0.util;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;

/**
 * saves AppUser to currentUser
 * Allows program to easily access what the current User is
 */

public class CurrentUser {
    public static AppUser currentUser;
    public static UserAccount currentAccount;

    public static UserAccount getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(UserAccount currentAccount) {
        CurrentUser.currentAccount = currentAccount;
    }

    public CurrentUser() {
    }

    public static AppUser getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(AppUser currentUser) {
        CurrentUser.currentUser = currentUser;
    }
}
