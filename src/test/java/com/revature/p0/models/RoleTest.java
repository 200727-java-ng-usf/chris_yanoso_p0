package com.revature.p0.models;

import org.junit.Assert;
import org.junit.Test;

public class RoleTest {

    @Test
    public void getRoleByName(){
        Role expectedRole = Role.ACCOUNT_HOLDER;
        String insertString = "Account Holder";
        Role actualRole = Role.getByName(insertString);
        Assert.assertEquals(expectedRole,actualRole);
    }

    @Test
    public void getRoleByNameFailed(){
        Role expectedRole = Role.CLOSED;
        String insertString = "bad input";
        Role actualRole = Role.getByName(insertString);
        Assert.assertEquals(expectedRole,actualRole);
    }
}
