package com.revature.p0.models;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class UserAccount {
    private int id;
    private float balance;
    private int user_id;

    public UserAccount(int id, float balance, int user_id) {
        this.id = id;
        this.balance = balance;
        this.user_id = user_id;
    }


    public UserAccount() {

    }

    public int getId() {
        return id;
    }

    public float getBalance() {
        return balance;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
