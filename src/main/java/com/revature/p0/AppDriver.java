package com.revature.p0;

import com.revature.p0.repos.UserRepository;
import com.revature.p0.screens.*;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppDriver {

    static UserRepository userRepo = new UserRepository();
    static UserService userService = new UserService(userRepo);
    public static void main(String[] args) throws IOException {


        System.out.println("Welcome to Revature Project bank!");
        main2();
        main3();


    }

    public static void main2() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        boolean goodUserInput = false;
        String option;
        do {
            System.out.println("Please Select one of the following options: " +
                    "\n     1. Register a new account" +
                    "\n     2. Login");
            option = console.readLine();
            if (option.trim().equals("1") || option.trim().equals("2")) {
                goodUserInput = true;
            } else {
                System.out.println(option + " was not a valid option.");
            }
        } while (!goodUserInput);

        switch (option) {
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
        }
    }

    public static void main3() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        boolean goodUserInput = false;
        String option;
        do {
            System.out.println("Please Select one of the following options: " +
                    "\n     1. Deposit" +
                    "\n     2. Withdraw" +
                    "\n     3. View Balance" +
                    "\n     4. Log out");
            option = console.readLine();
            if (option.trim().equals("1") || option.trim().equals("2") || option.trim().equals("3") || option.trim().equals("4")) {
                goodUserInput = true;
            } else {
                System.out.println(option + " was not a valid option.");
            }
        } while (!goodUserInput);

        switch (option) {
            case "1":
                DepositScreen depositScreen = new DepositScreen();
                depositScreen.render();
                break;
            case "2":
                WithdrawScreen withdrawScreen = new WithdrawScreen();
                withdrawScreen.render();
                break;
            case "3":
                BalanceScreen balanceScreen = new BalanceScreen();
                balanceScreen.render();
                break;
            case "4":
                logoff();
                break;
            default:
                System.out.println(option + " is not a valid option!");

                break;
        }
    }

    public static void logoff(){
        System.out.println("Log out successful!");
        System.out.println("Have a nice day!");
    }
}



