package com.revature.p0.models;

import com.revature.p0.services.AccountService;
import org.junit.Assert;
import org.junit.Test;

public class UserAccountTest {
    int id = 1;
    float balance = 5.6f;
    int user_id = 2;

    @Test
    public void newUserAccountGettersAndSetters(){
        UserAccount ua = new UserAccount();
        ua.setId(id);
        ua.setBalance(balance);
        ua.setUser_id(user_id);
        int actualId = ua.getId();
        float actualBalance = ua.getBalance();
        int actualUserId = ua.getUser_id();
        Assert.assertEquals(id,actualId);
        Assert.assertEquals(balance,actualBalance,0.0000);
        Assert.assertEquals(user_id,actualUserId);
    }

}
