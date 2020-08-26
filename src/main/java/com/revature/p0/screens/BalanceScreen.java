package com.revature.p0.screens;


import com.revature.p0.util.CurrentUser;

import java.io.IOException;


import static com.revature.p0.AppDriver.app;

/**
 * Screen that handles balance
 */

public class BalanceScreen extends Screen {
    public BalanceScreen() {
        super("BalanceScreen", "/balance");
    }

    @Override
    public void render() throws IOException {
        float balance = CurrentUser.getCurrentAccount().get().getBalance();
        System.out.println("Your current balance is: $" + balance);
        app.getRouter().navigate("/dash");
    }



}

