package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.services.UserService;
import com.revature.p0.user.CurrentUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginScreen implements Screen {

    private UserService userService;

    public LoginScreen(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void render() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String username, password;

        try {
            System.out.println("Please provide your login credentials");
            System.out.print("Username: ");
            username = console.readLine();
            System.out.print("Password: ");
            password = console.readLine();

            AppUser authUser = userService.authenticate(username, password);
            System.out.println(authUser);
            //Sets app's current user to be the logged in one
            if (authUser != null){
                CurrentUser.setCurrentUser(authUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
