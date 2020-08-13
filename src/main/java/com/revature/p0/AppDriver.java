package com.revature.p0;

import com.revature.p0.screens.RegisterScreen;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppDriver {

    public static void main(String[] args) throws IOException {
        // UserRepository userRepo = new UserRepository();
        // UserService userService = new UserService(userRepo);

        System.out.println("Welcome to Revature Project bank!");

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        boolean goodUserInput = false;
        do {
            System.out.println("Please Select one of the following options: " +
                               "\n     1. Register a new account" +
                               "\n     2. Login");
            String option = console.readLine();
            if (option.trim().equals("1") || option.trim().equals("2")){
                goodUserInput = true;
            } else {
                System.out.println(option + " was not a valid option.");
            }
        } while (!goodUserInput);

        /*switch (option){
            case "1":
                RegisterScreen registerScreen = new RegisterScreen(userService);
                registerScreen.render();
                break;
            case "2":
                LoginScreen loginScreen = new LoginScreen(userService);
                loginScreen.render();
                break;
            default:
                System.out.println(option + " is not a valid option!");

                break;
        }*/
    }



}
