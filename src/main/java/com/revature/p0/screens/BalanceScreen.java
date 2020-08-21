package com.revature.p0.screens;

import java.io.IOException;

import static com.revature.p0.AppDriver.app;


public class BalanceScreen extends Screen {
    public BalanceScreen() {
        super("BalanceScreen", "/balance");
    }

    @Override
    public void render() throws IOException {
        System.out.println("Balance Screen reached!");
        app.getRouter().navigate("/dash");
    }

}
