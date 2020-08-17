package com.revature.p0.repos;

import com.revature.p0.db.UserDB;
import com.revature.p0.models.AppUser;

import java.io.IOException;

public class UserRepository {
    /**
     * Constructs user database
     * has methods to find users by credentials/username
     * can save new users to database
     */

    private UserDB userDataSet = UserDB.userDataSet;

    public UserRepository(){
        super();
    }



    public AppUser findUserByCredentials(String userName, String password) {
        return userDataSet.findUserByCredentials(userName, password);
    }

    public AppUser findUserByUserName(String userName){
        return userDataSet.findUserByUserName(userName);
    }

    public AppUser save(AppUser newUser) throws IOException {
        userDataSet.addNewUserToFile(newUser);
        return userDataSet.addUser(newUser);
    }
}
