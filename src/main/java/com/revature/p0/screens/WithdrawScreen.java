package com.revature.p0.screens;

import java.io.IOException;

import static com.revature.p0.AppDriver.main3;

public class WithdrawScreen implements Screen {
    @Override
    public void render() throws IOException {
        System.out.println("Withdraw Screen reached!");

        main3();
    }
}
