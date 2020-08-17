package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.p0.AppDriver.main2;

/**
 * uses BufferedReader to get users info
 * uses userService.register method to check if info is valid/original
 * saves user to the repo
 */

public class RegisterScreen implements Screen{

    private UserService userService;

    public RegisterScreen(UserService userService) {
        this.userService = userService;
    }

    @Override
   public void render() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String firstName, lastName, username, password;

        try {

            System.out.println("Sign up for a new account!");
            System.out.print("First name: ");
            firstName = console.readLine();
            System.out.print("Last name: ");
            lastName = console.readLine();
            System.out.print("Username: ");
            username = console.readLine();
            System.out.print("Password: ");
            password = console.readLine();

            AppUser newUser = new AppUser(firstName, lastName, username, password);
            AppUser registeredUser = userService.register(newUser);
            System.out.println(registeredUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
        main2();

    }
}
