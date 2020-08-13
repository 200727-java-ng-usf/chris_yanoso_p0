package com.revature.p0.db;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.Role;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class UserDB extends HashMap<Integer, AppUser> {
    public static UserDB userDataset = new UserDB();
    public static Integer key = 1;

    static{
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/RegisteredUsers.txt"));
            String line = reader.readLine();
            while (line != null) {
                String [] userFields = ":".split(line);
                AppUser newUser = new AppUser(userFields[0],userFields[1],userFields[2],userFields[3]);
                userDataset.addUser(newUser);

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception ioe){
            ioe.printStackTrace();
            System.out.println("An exception occurred while reading RegisteredUsers.txt");
        }

    }

    public AppUser addUser(AppUser newUser){
        AppUser nUser = new AppUser(newUser);
        nUser.setId(key);
        userDataset.put(key++, nUser);
        return nUser;

    }

    public AppUser findUserByCredentials(String userName, String password){

        for(AppUser user: userDataset.values()) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public AppUser findUserByUserName(String userName) {

        for(AppUser user: userDataset.values()) {
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    public AppUser findUserById(int id) {

        for(AppUser user: userDataset.values()){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

}
