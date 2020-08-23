package com.revature.p0.screens;

import com.revature.p0.services.AccountService;
import com.revature.p0.util.CurrentUser;

import java.io.IOException;
import java.util.InputMismatchException;

import static com.revature.p0.AppDriver.app;


public class WithdrawScreen extends Screen {
    public WithdrawScreen() {
        super("WithdrawScreen", "/withdraw");
    }

    @Override
    public void render() throws IOException {
        boolean success = false;
        float withdrawAmount;
        while (!success) {
            try {
                boolean goodWithdraw = false;
                while(!goodWithdraw) {
                    System.out.println("Please enter the amount you wish to withdraw");
                    withdrawAmount = Float.parseFloat((app.getConsole().readLine()));
                    float balance = CurrentUser.getCurrentAccount().get().getBalance();
                    if (withdrawAmount > balance) {
                        System.out.println("You do not have enough funds in your account");
                    }else if (withdrawAmount == 0){
                        System.out.println("You can not withdraw 0 dollars");
                    } else if (withdrawAmount < 0){
                        System.out.println("You can not withdraw a negative amount");
                    }
                    else {
                        balance -= withdrawAmount;
                        goodWithdraw = true;
                        AccountService.updateBalance(balance);
                        CurrentUser.setCurrentAccount(AccountService.setCurrentAccount(CurrentUser.getCurrentUser()));
                        System.out.println("$" + withdrawAmount + " has been withdrawn from your account. Your new balance is: $" + balance);
                    }
                }
                success = true;
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number");
            } catch (Exception e) {
                System.out.println("An exception has occurred: " + e);;
            }
        }
        app.getRouter().navigate("/dash");
    }
}
