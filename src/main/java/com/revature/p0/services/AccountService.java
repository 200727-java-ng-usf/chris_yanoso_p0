package com.revature.p0.services;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;
import com.revature.p0.repos.AccountRepo;
import com.revature.p0.util.CurrentUser;

import java.util.InputMismatchException;
import java.util.Optional;

import static com.revature.p0.AppDriver.app;

/**
 * Account services, screens will use these methods
 */
public class AccountService {

    private AccountRepo accountRepo;

    /**
     * creates the account service, used on start up
     * @param accountRepo
     */
    public AccountService(AccountRepo accountRepo) {
        super();
        this.accountRepo = accountRepo;
    }

    /**
     * creates a user account in java, used on log in
     * @param user
     */
    public void setCurrentAccount(Optional<AppUser> user){
        int userId = user.get().getId();
        Optional<UserAccount> _account = accountRepo.getCurrentAccount(userId);
        CurrentUser.setCurrentAccount(_account);

    }

    /**
     * updates balance after a transaction
     * @param balance
     */
    public void updateBalance(float balance){
        accountRepo.updateBalance(balance);
    }

    /**
     * takes an input from user
     * adds those funds into the balance if the input is good (not negative, and a number)
     * @param balance
     */
    public void depositIntoAccount(float balance) {
        boolean success = false;
        float depositAmount;
        while (!success) {
            try {
                boolean goodDeposit = false;
                while (!goodDeposit) {
                    System.out.print("\nPlease enter the amount you wish to deposit");
                    depositAmount = Float.parseFloat((app.getConsole().readLine()));
                    if (depositAmount < 0) {
                        System.out.print("\nYou can not deposit a negative amount");
                    } else {
                        balance += depositAmount;
                        updateBalance(balance);
                        goodDeposit = true;
                        System.out.print("\n$" + depositAmount + " has been deposited.");
                    }
                }
                success = true;
            } catch (InputMismatchException ime) {
                System.out.print("\nPlease enter a valid number");
            } catch (NumberFormatException nfe){
                System.out.print("\nPlease enter a number");
            }catch (Exception e) {
                System.out.print("\nAn exception has occurred: " + e);
            }
        }
    }

    /**
     * Method that takes money from user account
     * used by withdraw screen and currency exchange screen
     * only withdraws if valid (is a number, not negative, not more than in balance)
     * @param balance
     * @return
     */
    public float WithdrawFromAccount(float balance){
        boolean success = false;
        float withdrawAmount = 0;
        while (!success) {
            try {
                boolean goodWithdraw = false;
                while(!goodWithdraw) {
                    System.out.print("\nPlease enter the amount you wish to withdraw");
                    withdrawAmount = Float.parseFloat((app.getConsole().readLine()));
                    if (withdrawAmount > balance) {
                        System.out.print("\nYou do not have enough funds in your account");
                    } else if (withdrawAmount < 0){
                        System.out.print("\nYou can not withdraw a negative amount");
                    } else {
                        balance -= withdrawAmount;
                        goodWithdraw = true;
                        updateBalance(balance);

                    }
                }
                success = true;
            } catch (InputMismatchException ime) {
                System.out.print("\nPlease enter a valid number");
            }catch (NumberFormatException nfe){
                System.out.print("\nPlease enter a number");
            }
            catch (Exception e) {
                System.out.println("An exception has occurred: " + e);
            }

        }
        return withdrawAmount;
    }
}
