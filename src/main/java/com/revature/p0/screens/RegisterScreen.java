package com.revature.p0.screens;

import com.revature.p0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterScreen implements Screen{

    private UserService userService;

    public RegisterScreen(UserService userService) {
        this.userService = userService;
    }

    @Override
   public void render() {
        /*BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
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
        }*/
    }
}
