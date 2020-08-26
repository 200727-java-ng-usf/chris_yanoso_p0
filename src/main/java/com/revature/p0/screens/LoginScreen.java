package com.revature.p0.screens;

import com.revature.p0.exceptions.AuthenticationException;
import com.revature.p0.models.AppUser;
import com.revature.p0.repos.AccountRepo;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.CurrentUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static com.revature.p0.AppDriver.app;

/**
 * Login screen that asks user for username and password
 * checks if username and password are correct
 * if login is successful, saves appUser to currentUser to be used elsewhere and continues
 * if not do while loop will loop until login success
 */

public class LoginScreen extends Screen {

    private UserService userService;
    private AccountService accountService;



    public LoginScreen(UserService userService, AccountService accountService) {
        super("LoginScreen", "/login");
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void render() throws IOException {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String username, password;
        boolean success = false;
        try {
            do {
                System.out.println("Please provide your login credentials");
                System.out.print("Username: ");
                username = console.readLine();
                System.out.print("Password: ");
                password = console.readLine();
                //when user is returned from db the role is off by 1
                Optional<AppUser> authUser = userService.authenticate(username, password);
                //Sets app's current user to be the logged in one
                if (authUser.isPresent()) {
                    CurrentUser.setCurrentUser(authUser);
                    accountService.setCurrentAccount(authUser);
                    success = true;
                }
            } while (!success);
            app.getRouter().navigate("/dash");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
