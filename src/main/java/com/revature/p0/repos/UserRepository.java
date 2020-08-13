package com.revature.p0.repos;

import com.revature.p0.db.UserDB;
import com.revature.p0.models.AppUser;

public class UserRepository {

    private UserDB userDataset = UserDB.userDataset;

    public UserRepository(){
        super();
    }

    public AppUser findUserByCredentials(String userName, String password) {
        return userDataset.findUserbyCredentials(userName, password);
    }
}
