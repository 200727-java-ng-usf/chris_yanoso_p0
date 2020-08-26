package com.revature.p0.util;

import com.revature.p0.models.UserAccount;
import com.revature.p0.repos.AccountRepo;
import com.revature.p0.repos.UserRepository;
import com.revature.p0.screens.*;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ScreenUtil {

    private BufferedReader console;
    private ScreenRouter router;
    private boolean appRunning;

    public ScreenUtil(){
        appRunning = true;
        console = new BufferedReader(new InputStreamReader(System.in));

        final UserRepository userRepo = new UserRepository();
        final AccountRepo accountRepo = new AccountRepo();
        final UserService userService = new UserService(userRepo);
        final AccountService accountService = new AccountService(accountRepo);

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
              .addScreen(new RegisterScreen(userService))
              .addScreen(new LoginScreen(userService, accountService))
              .addScreen(new DashboardScreen())
              .addScreen(new DepositScreen(userService, accountService))
              .addScreen(new WithdrawScreen(accountService))
              .addScreen(new BalanceScreen())
              .addScreen(new CurrencyExchangeScreen(userService, accountService));
    }

    public BufferedReader getConsole() {
        return console;
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }
}
