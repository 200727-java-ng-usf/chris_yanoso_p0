package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
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



    public LoginScreen(UserService userService) {
        super("LoginScreen", "/login");
        this.userService = userService;
    }

    @Override
    public void render() throws IOException {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String username, password;
        boolean success = false;

        do{
            System.out.println("Please provide your login credentials");
            System.out.print("Username: ");
            username = console.readLine();
            System.out.print("Password: ");
            password = console.readLine();
            //when user is returned from db the role is off by 1
            Optional<AppUser> authUser = userService.authenticate(username, password);
            //Sets app's current user to be the logged in one
            if (authUser.isPresent()){
                CurrentUser.setCurrentUser(authUser);
                CurrentUser.setCurrentAccount(AccountService.setCurrentAccount(authUser));
                success = true;
                }
        } while (!success);
        app.getRouter().navigate("/dash");
    }
}
