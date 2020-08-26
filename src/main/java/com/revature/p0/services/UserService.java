package com.revature.p0.services;

import com.revature.p0.exceptions.AuthenticationException;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.Role;
import com.revature.p0.repos.UserRepository;
import com.revature.p0.util.CurrentUser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
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

    // either return an AppUser or void; (set currentUser in here for simplicity)
    public Optional<AppUser> authenticate(String userName, String password) throws IOException {

        if (userName == null || userName.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid username/password provided");
        }

        Optional<AppUser> authenticatedUser =userRepo.findUserByCredentials(userName, password);
        // mock userrepo
        // when/then
        // Mockito.when(userRepo.findUserByCredentials(Mockito.anyString(), Mockito.anyString())
        //        .thenReturn(Optional.empty());

        // Mockito.when(userRepo.findUserByCredentials(Mockito.anyString(), Mockito.anyString())
        //        .thenReturn(Optional.of(new AppUser());

        if (!authenticatedUser.isPresent()) {
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

    public void register(AppUser newUser) throws IOException {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user field values provided during registration!");
        }

       if (userRepo.findUserByUserName(newUser.getUserName()).isPresent()) {
            throw new ResourcePersistenceException("Provided username is already in use!");
       }

        newUser.setRole(Role.ACCOUNT_HOLDER);
        userRepo.save(newUser);
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

    /**
     * makes sure that the new user is valid (not null or empty string)
     * @param user
     * @return
     */

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUserName() == null || user.getUserName().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }

}
