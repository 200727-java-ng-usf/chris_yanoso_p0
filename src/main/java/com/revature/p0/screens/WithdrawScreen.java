package com.revature.p0.screens;

import java.io.IOException;

import static com.revature.p0.AppDriver.app;


public class WithdrawScreen extends Screen {
    public WithdrawScreen() {
        super("WithdrawScreen", "/withdraw");
    }

    @Override
    public void render() throws IOException {
        System.out.println("Withdraw Screen reached!");
        app.getRouter().navigate("/dash");
    }
}
