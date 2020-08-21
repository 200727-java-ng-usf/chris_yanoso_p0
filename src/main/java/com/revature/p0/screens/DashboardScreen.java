package com.revature.p0.screens;

import com.revature.p0.models.UserAccount;
import com.revature.p0.util.CurrentUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.p0.AppDriver.app;
import static com.revature.p0.AppDriver.logoff;

public class DashboardScreen extends Screen {
    public DashboardScreen() {
        super("DashboardScreen", "/dash");
    }

    @Override
    public void render() throws IOException {
        System.out.println("Dash reached");
        boolean goodUserInput = false;
        String option;
        UserAccount currentAccount = new UserAccount(CurrentUser.getCurrentUser());
        CurrentUser.setCurrentAccount(currentAccount);
        do {
            System.out.println("Please Select one of the following options: " +
                    "\n     1. Deposit" +
                    "\n     2. Withdraw" +
                    "\n     3. View Balance" +
                    "\n     4. Log out");
            option = app.getConsole().readLine().trim();
            if (option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")) {
                goodUserInput = true;
            } else {
                System.out.println(option + " was not a valid option.");
            }
        } while (!goodUserInput);

        switch (option) {
            case "1":
                app.getRouter().navigate("/deposit");
                break;
            case "2":
                app.getRouter().navigate("/withdraw");
                break;
            case "3":
                app.getRouter().navigate("/balance");
                break;
            case "4":
                logoff();
                break;
            default:
                System.out.println(option + " is not a valid option!");

                break;
        }
    }

    }

