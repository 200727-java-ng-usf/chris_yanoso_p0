package com.revature.p0.db;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.Role;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class UserDB extends HashMap<Integer, AppUser> {

    public static UserDB userDataSet = new UserDB();
    public static Integer key = 1;

    static{
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/AccountHolderUsers.txt"));
            String line = reader.readLine();
            while (line != null) {
                String [] userFields = line.split(":");
                AppUser newUser = new AppUser();
                newUser.setFirstName(userFields[0]);
                newUser.setLastName(userFields[1]);
                newUser.setUserName(userFields[2]);
                newUser.setPassword(userFields[3]);
                userDataSet.addUser(newUser);

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception ioe){
            ioe.printStackTrace();
            System.out.println("An exception occurred while reading AccountHolderUsers.txt");
        }

    }

    public AppUser addUser(AppUser newUser){
        AppUser nUser = new AppUser(newUser);
        nUser.setId(key);
        userDataSet.put(key++, nUser);
        return nUser;

    }

    public AppUser findUserByCredentials(String userName, String password){

        for(AppUser user: userDataSet.values()) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public AppUser findUserByUserName(String userName) {

        for(AppUser user: userDataSet.values()) {
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    public AppUser findUserById(int id) {

        for(AppUser user: userDataSet.values()){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public void addNewUserToFile(AppUser newUser) throws IOException {
        File file = new File("src/main/resources/AccountHolderUsers.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        String data = newUser.getFirstName() + ":" + newUser.getLastName() + ":" + newUser.getUserName() + ":" + newUser.getPassword();
        br.write("\n" + data);

        br.close();
        fr.close();
    }

}
