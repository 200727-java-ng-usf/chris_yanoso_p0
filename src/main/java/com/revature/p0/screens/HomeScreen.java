package com.revature.p0.screens;

import java.io.IOException;

import static com.revature.p0.AppDriver.app;

/**
 * Landing page on startup
 * Will come back here after register or logoff
 */

public class HomeScreen extends Screen {
    public HomeScreen() {
        super("HomeScreen", "/home");
    }

    @Override
    public void render() throws IOException {

        String option;

            System.out.println("Please Select one of the following options: " +
                    "\n     1. Register a new account" +
                    "\n     2. Login" +
                    "\n     3. End Program");
            option = app.getConsole().readLine().trim();

        switch (option) {
            case "1":
                app.getRouter().navigate("/register");
                break;
            case "2":
                app.getRouter().navigate("/login");
                break;
            case "3":
                app.setAppRunning(false);
                break;
            default:
                System.out.println(option + " is not a valid option!");

                break;
        }
    }
}
