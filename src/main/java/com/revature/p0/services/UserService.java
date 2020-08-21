package com.revature.p0.services;

import com.revature.p0.exceptions.AuthenticationException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.Role;
import com.revature.p0.repos.UserRepository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;



public class UserService {
    /**
     * class that has methods to deal with user input about AppUsers
     */

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * LoginScreen uses authenticate
     * checks to see if a user in userRepo has the same username and password
     * @param userName
     * @param password
     * @return
     * @throws IOException
     */

    public AppUser authenticate(String userName, String password) throws IOException {

        if (userName == null || userName.trim().equals("") || password == null || password.trim().equals("")) {
            // TODO implement a custom InvalidRequestException
            throw new RuntimeException("Invalid username/password provided");
        }

        AppUser authenticatedUser =userRepo.findUserByCredentials(userName, password);

        if (authenticatedUser == null) {
            //TODO implement a custom AuthenticationException
            throw new AuthenticationException("No user found with the provided credentials");

        }

        return authenticatedUser;
    }

    /**
     * RegisterScreen uses register
     * checks to see if user input info is valid
     * checks to see if it is original
     * @param newUser
     * @return
     * @throws IOException
     */

    public AppUser register(AppUser newUser) throws IOException {

        if (!isUserValid(newUser)) {
            //TODO implement a custom InvalidRequestException
            throw new RuntimeException("Invalid user field values provided during registration!");
        }

        if (userRepo.findUserByUserName(newUser.getUserName()) != null) {
            //TODO implement a custom ResourcePersistenceException
            throw new RuntimeException("Provided username is already in use!");
        }

        newUser.setRole(Role.ACCOUNT_HOLDER);
        return userRepo.save(newUser);
    }

    public Set<AppUser> getAllUsers(){
        return new HashSet<>();
    }
    public Set<AppUser> getUsersByRole() {
        return new HashSet<>();
    }

    public AppUser getUserById(int id) {
        return null;
    }

    public AppUser getUserByUsername(String username) {
        return null;
    }

    public boolean deleteUserById(int id) {
        return false;
    }

    public boolean update(AppUser updatedUser) {
        return false;
    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUserName() == null || user.getUserName().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }
}
