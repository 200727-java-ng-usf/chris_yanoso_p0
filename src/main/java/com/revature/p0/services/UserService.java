package com.revature.p0.services;

import com.revature.p0.models.AppUser;
import com.revature.p0.repos.UserRepository;

public class UserService {

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public AppUser authenticate(String userName, String password){

        if (userName == null || userName.trim().equals("") || password == null || password.trim().equals("")) {
            // TODO implement a custom InvalidRequestException
            throw new RuntimeException("Invalid username/password provided");
        }

        AppUser authenticatedUser =userRepo.findUserByCrendentials(userName, password);
    }
}
