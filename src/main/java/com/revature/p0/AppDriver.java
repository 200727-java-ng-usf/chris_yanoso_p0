package com.revature.p0;

import com.revature.p0.repos.UserRepository;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenUtil;

import java.io.IOException;

public class AppDriver {
    /**
     * This Driver will cycling through options and direct user to where they want to go
     */

    public static ScreenUtil app = new ScreenUtil();
    static UserRepository userRepo = new UserRepository();
    static UserService userService = new UserService(userRepo);
    public static void main(String[] args) throws IOException {


        System.out.println("Welcome to Revature Project bank!");
        while (app.isAppRunning()) {
            app.getRouter().navigate("/home");
        }


    }

    /**
     * Login or Register new account?
     * Successful Register will return to main2
     * Successful Login will continue to driver (main3)
     * @throws IOException
     */



    /**
     * After successful login app will ask user what they want to do
     * switch case that will direct user to the appropriate screen
     * @throws IOException
     */



    /**
     * ends the app, current only end of app
     */
    public static void logoff(){
        System.out.println("Log out successful!");
        System.out.println("Have a nice day!");
    }
}



