package com.revature.p0.screens;

import com.revature.p0.repos.AccountRepo;
import com.revature.p0.services.AccountService;
import com.revature.p0.util.CurrentUser;

import java.io.IOException;
import java.util.InputMismatchException;

import static com.revature.p0.AppDriver.app;

/**
 * withdraws money from user account via withdraw method
 */
public class WithdrawScreen extends Screen {
    private AccountService accountService;
    public WithdrawScreen(AccountService accountService) {
        super("WithdrawScreen", "/withdraw");
        this.accountService = accountService;
    }

    @Override
    public void render() throws IOException {
        float balance = CurrentUser.getCurrentAccount().get().getBalance();
       float withdrawAmount = accountService.WithdrawFromAccount(balance);
        accountService.setCurrentAccount(CurrentUser.getCurrentUser());
        balance = CurrentUser.getCurrentAccount().get().getBalance();
        System.out.println("$" + withdrawAmount + " has been withdrawn from your account. Your new balance is: $" + balance);
        app.getRouter().navigate("/dash");
    }
}
