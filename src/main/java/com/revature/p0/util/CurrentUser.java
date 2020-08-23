package com.revature.p0.util;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;

import java.util.Optional;

/**
 * saves AppUser to currentUser
 * Allows program to easily access what the current User is
 */

public class CurrentUser {
    public static Optional<AppUser> currentUser;
    public static Optional<UserAccount> currentAccount;

    public static Optional<UserAccount> getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Optional<UserAccount> currentAccount) {
        CurrentUser.currentAccount = currentAccount;
    }

    public CurrentUser() {
    }

    public static Optional<AppUser> getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Optional<AppUser> currentUser) {
        CurrentUser.currentUser = currentUser;
    }
}
