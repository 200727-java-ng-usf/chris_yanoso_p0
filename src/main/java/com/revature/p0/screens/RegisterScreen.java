package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static com.revature.p0.AppDriver.app;


/**
 * uses BufferedReader to get users info
 * uses userService.register method to check if info is valid/original
 * saves user to the repo
 */

public class RegisterScreen extends Screen{

    private UserService userService;

    public RegisterScreen(UserService userService) {
        super("RegisterScreen", "/register");
        this.userService = userService;
    }

    @Override
   public void render() throws IOException {
        String firstName, lastName, username, password;

        try {

            System.out.println("Sign up for a new account!");
            System.out.print("First name: ");
            firstName = app.getConsole().readLine().trim();
            System.out.print("Last name: ");
            lastName = app.getConsole().readLine().trim();
            System.out.print("Username: ");
            username = app.getConsole().readLine().trim();
            System.out.print("Password: ");
            password = app.getConsole().readLine().trim();

            AppUser newUser = new AppUser(firstName, lastName, username, password);
             userService.register(newUser);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
