package com.revature.p0.models;

import java.io.File;
import java.io.IOException;

public class UserAccount {
    private AppUser thisUser;
    private File accountFile;

    public UserAccount(){super();}

    public UserAccount(AppUser appUser) throws IOException {
        this.thisUser = appUser;
        File account = new File("src/main/resources/" + appUser.getUserName() + ".txt");
        boolean exists = account.exists();
        if (!exists) account.createNewFile();
        this.accountFile = account;
    }

}
