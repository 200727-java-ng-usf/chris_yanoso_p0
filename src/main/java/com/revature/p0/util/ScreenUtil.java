package com.revature.p0.util;

import com.revature.p0.repos.UserRepository;
import com.revature.p0.screens.*;
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
        final UserService userService = new UserService(userRepo);

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
              .addScreen(new RegisterScreen(userService))
              .addScreen(new LoginScreen(userService))
              .addScreen(new DashboardScreen())
              .addScreen(new DepositScreen(userService))
              .addScreen(new WithdrawScreen())
              .addScreen(new BalanceScreen());
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
