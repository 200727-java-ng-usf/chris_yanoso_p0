package com.revature.p0.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppUserTest {
    String first = "test1";
    String last = "test2";
    String user = "test3";
    String pass = "test4";
    Role role = Role.ACCOUNT_HOLDER;
    int id = 1;

    @Test
    public void newUserTest(){
        AppUser testUser = new AppUser(first, last, user, pass, role, id);
        String expected = "AppUser{" +
                "id=1" +
                ", firstName='" + first + '\'' +
                ", lastName='" + last+ '\'' +
                ", userName='" + user + '\'' +
                ", password='" + pass + '\'' +
                ", role=" + role +
                '}';
        String actual = testUser.toString();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void appUserSetters(){
        AppUser testUser = new AppUser();
        testUser.setId(id);
        testUser.setRole(role);
        testUser.setFirstName(first);
        testUser.setLastName(last);
        testUser.setUserName(user);
        testUser.setPassword(pass);
        String expected = "AppUser{" +
                "id=1" +
                ", firstName='" + first + '\'' +
                ", lastName='" + last+ '\'' +
                ", userName='" + user + '\'' +
                ", password='" + pass + '\'' +
                ", role=" + role +
                '}';
        String actual = testUser.toString();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void appUserGetters(){
        AppUser testUser = new AppUser(first, last, user, pass, role, id);
        String actualFirst = testUser.getFirstName();
        String actualLast = testUser.getLastName();
        String actualUser = testUser.getUserName();
        String actualPass = testUser.getPassword();
        Role actualRole = testUser.getRole();
        int actualId = testUser.getId();
        Assert.assertEquals(first,actualFirst);
        Assert.assertEquals(last,actualLast);
        Assert.assertEquals(user,actualUser);
        Assert.assertEquals(pass,actualPass);
        Assert.assertEquals(role,actualRole);
        Assert.assertEquals(id,actualId);
    }

}
