package com.revature.p0.screens;

import com.revature.p0.repos.AccountRepo;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.CurrentUser;

import java.io.IOException;
import java.util.InputMismatchException;

import static com.revature.p0.AppDriver.app;

public class CurrencyExchangeScreen extends Screen {
    private UserService userService;
    private AccountService accountService;

    public CurrencyExchangeScreen(UserService userService, AccountService accountService) {
        super("Currency Exchange Screen", "/currency");
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void render() throws IOException {


        String option;

        System.out.println("Please Select the Currency you would like to withdraw in: " +
                "\n     1. Euro" +
                "\n     2. Pounds" +
                "\n     3. Mexican peso" +
                "\n     4. Canadian dollar" +
                "\n     5. Argentine Peso");
        option = app.getConsole().readLine().trim();
        float balance = CurrentUser.getCurrentAccount().get().getBalance();
        float withdrawAmount= accountService.WithdrawFromAccount(balance);

            switch (option) {
                case "1":
                    withdrawAmount *= 0.85;
                    System.out.println("You now have " + withdrawAmount + " Euros");
                    break;
                case "2":
                    withdrawAmount *= 0.77;
                    System.out.println("You now have " + withdrawAmount + " Pound Sterling");
                    break;
                case "3":
                    withdrawAmount *= 22.02;
                    System.out.println("You now have " + withdrawAmount + " Mexican Pesos");
                    break;
                case "4":
                    withdrawAmount *= 1.32;
                    System.out.println("You now have " + withdrawAmount + " Canadian Dollar");
                    break;
                case "5":
                    withdrawAmount *= 73.73;
                    System.out.println("You now have " + withdrawAmount + " Argentine Peso");
                    break;
                default:
                    System.out.println(option + " is not a valid option!");

                    break;
            }
        accountService.setCurrentAccount(CurrentUser.getCurrentUser());
        balance = CurrentUser.getCurrentAccount().get().getBalance();
        System.out.println("you now have " + balance + " left in your account");
            app.getRouter().navigate("/dash");
        }
    }
