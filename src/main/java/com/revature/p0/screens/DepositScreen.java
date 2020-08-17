package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;
import com.revature.p0.services.UserService;
import com.revature.p0.user.CurrentUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.p0.AppDriver.main3;

/**
 * asks user for deposit
 * adds to money to account
 */

public class DepositScreen implements Screen {

    private UserService userService;
    private UserAccount currentAccount;

    public DepositScreen(UserService userService){
        this.userService = userService;
        this.currentAccount = CurrentUser.getCurrentAccount();
    }
    @Override
    public void render() throws IOException {


        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        int depositAmount;
        System.out.println("DepositScreen reached!");

        try {
            System.out.println("Please enter the amount you wish to deposit");
            depositAmount = console.read();

        } catch (Exception e){
            e.printStackTrace();
        }

        main3();
    }
}
