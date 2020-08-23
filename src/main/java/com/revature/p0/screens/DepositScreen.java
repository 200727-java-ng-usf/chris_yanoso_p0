package com.revature.p0.screens;

import com.revature.p0.models.UserAccount;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.CurrentUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

import static com.revature.p0.AppDriver.app;


/**
 * asks user for deposit
 * adds to money to account
 */

public class DepositScreen extends Screen {

    private UserService userService;

    public DepositScreen(UserService userService){
        super("DepositScreen", "/deposit");
        this.userService = userService;
    }
    @Override
    public void render() throws IOException {

        boolean success = false;
        float depositAmount;
        while (!success) {
            try {
                float balance = CurrentUser.getCurrentAccount().get().getBalance();
                boolean goodDeposit = false;
                while(!goodDeposit) {
                    System.out.println("Please enter the amount you wish to deposit");
                    depositAmount = Float.parseFloat((app.getConsole().readLine()));
                    if (depositAmount < 0){
                        System.out.println("You can not deposit a negative amount");
                    } else {
                        balance += depositAmount;
                        AccountService.updateBalance(balance);
                        goodDeposit = true;
                        CurrentUser.setCurrentAccount(AccountService.setCurrentAccount(CurrentUser.getCurrentUser()));
                        System.out.println("$" + depositAmount + " has been deposited. Your new balance is $" + balance);
                    }
                }
                success = true;
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        app.getRouter().navigate("/dash");
    }
}
