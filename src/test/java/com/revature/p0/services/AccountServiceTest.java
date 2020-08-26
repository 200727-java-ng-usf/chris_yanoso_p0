package com.revature.p0.services;
import com.revature.p0.exceptions.AuthenticationException;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;
import com.revature.p0.repos.AccountRepo;
import com.revature.p0.repos.UserRepository;
import com.revature.p0.util.CurrentUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.revature.p0.exceptions.InvalidRequestException;
import org.mockito.Mock;
import org.mockito.Mockito;


import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
public class AccountServiceTest {
    private AccountRepo accountRepo = Mockito.mock(AccountRepo.class);
    private AccountService account;
    private CurrentUser currentUser = Mockito.mock(CurrentUser.class);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUp(){
        account = new AccountService(accountRepo);
        System.setOut(new PrintStream(outContent));
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testDepositIntoAccount(){
        float balance = 0;
        String s = "5";
        InputStream inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to deposit" + "\n$5.0 has been deposited.";
        account.depositIntoAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testBadInputDeposit(){
        float balance = 0;
        String s = "a\n5";
        InputStream inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to deposit" + "\nPlease enter a number" + "\nPlease enter the amount you wish to deposit" + "\n$5.0 has been deposited.";
        account.depositIntoAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void  testWithdrawFromAccount(){
        float balance = 56;
        String s = "5";
        InputStream inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        float expectedResult = 5;
        float withdrawAmount = account.WithdrawFromAccount(balance);
        Assert.assertEquals(expectedResult, withdrawAmount, 0.00);
    }

    @Test
    public void testBadInputWithdraw(){
        float balance = 56;
        String s = "n\n5";
        InputStream inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to withdraw" + "\nPlease enter a number" + "\nPlease enter the amount you wish to withdraw";
        float withdrawAmount = account.WithdrawFromAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testOverDraft(){
        float balance = 5;
        String s = "10\n5";
        InputStream inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to withdraw" + "\nYou do not have enough funds in your account" + "\nPlease enter the amount you wish to withdraw";
        float withdrawAmount = account.WithdrawFromAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testNegativeWithdraw(){
        float balance = 5;
        String s = "-10\n5";
        InputStream inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to withdraw" + "\nYou can not withdraw a negative amount" + "\nPlease enter the amount you wish to withdraw";
        float withdrawAmount = account.WithdrawFromAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testNegativeDeposit(){
        float balance = 0;
        String s = "-10\n5";
        InputStream inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to deposit" + "\nYou can not deposit a negative amount" + "\nPlease enter the amount you wish to deposit" + "\n$5.0 has been deposited.";
        account.depositIntoAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

}
