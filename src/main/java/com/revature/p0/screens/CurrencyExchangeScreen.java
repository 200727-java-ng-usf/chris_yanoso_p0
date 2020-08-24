package com.revature.p0.screens;

import com.revature.p0.services.AccountService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.CurrentUser;

import java.io.IOException;
import java.util.InputMismatchException;

import static com.revature.p0.AppDriver.app;

public class CurrencyExchangeScreen extends Screen {
    private UserService userService;

    public CurrencyExchangeScreen(UserService userService) {
        super("Currency Exchange Screen", "/currency");
        this.userService = userService;
    }

    @Override
    public void render() throws IOException {
        float balance = CurrentUser.getCurrentAccount().get().getBalance();

        String option;

        System.out.println("Please Select the Currency you would like to withdraw in: " +
                "\n     1. Euro" +
                "\n     2. Pounds" +
                "\n     3. Mexican peso" +
                "\n     4. Canadian dollar" +
                "\n     5. Argentine Peso");
        option = app.getConsole().readLine().trim();
        boolean success = false;
        float withdrawAmount = 0;
        while (!success) {
            try {
                boolean goodWithdraw = false;
                while (!goodWithdraw) {
                    System.out.println("Please enter the amount you wish to withdraw in USD");
                    withdrawAmount = Float.parseFloat((app.getConsole().readLine()));
                    if (withdrawAmount > balance) {
                        System.out.println("You do not have enough funds in your account");
                    } else if (withdrawAmount == 0) {
                        System.out.println("You can not withdraw 0 dollars");
                    } else if (withdrawAmount < 0) {
                        System.out.println("You can not withdraw a negative amount");
                    } else {
                        balance -= withdrawAmount;
                        goodWithdraw = true;
                        AccountService.updateBalance(balance);
                        CurrentUser.setCurrentAccount(AccountService.setCurrentAccount(CurrentUser.getCurrentUser()));
                    }
                }
                success = true;
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number");
            } catch (Exception e) {
                System.out.println("An exception has occurred: " + e);
                ;
            }
        }

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
                    System.out.println("You now have " + withdrawAmount + "Argentine Peso");
                    break;
                default:
                    System.out.println(option + " is not a valid option!");

                    break;
            }
            app.getRouter().navigate("/dash");
        }
    }
