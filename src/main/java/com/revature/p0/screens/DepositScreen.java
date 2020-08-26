package com.revature.p0.screens;

import com.revature.p0.repos.AccountRepo;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.CurrentUser;

import java.io.IOException;
import java.util.InputMismatchException;

import static com.revature.p0.AppDriver.app;


/**
 * asks user for deposit
 * adds to money to account via depositIntoAccount method
 */

public class DepositScreen extends Screen {

    private UserService userService;
    private AccountService accountService;

    public DepositScreen(UserService userService, AccountService accountService){
        super("DepositScreen", "/deposit");
        this.userService = userService;
        this.accountService = accountService;
    }
    @Override
    public void render() throws IOException {

        float balance = CurrentUser.getCurrentAccount().get().getBalance();
        accountService.depositIntoAccount(balance);
        accountService.setCurrentAccount(CurrentUser.getCurrentUser());
        balance = CurrentUser.getCurrentAccount().get().getBalance();
        System.out.print("\nYour new balance is $" + balance);
        app.getRouter().navigate("/dash");
    }
}
