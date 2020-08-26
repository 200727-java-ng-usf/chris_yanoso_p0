package com.revature.p0.services;
import com.revature.p0.exceptions.AuthenticationException;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;
import com.revature.p0.repos.AccountRepo;
import com.revature.p0.repos.UserRepository;
import com.revature.p0.util.CurrentUser;
import org.junit.*;
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
    private AccountRepo accountRepo;
    private AccountService account;
    private ByteArrayOutputStream outContent;
    private InputStream inContent;
    private String s;
    @Before
    public void setUp(){
        accountRepo  = Mockito.mock(AccountRepo.class);
        account = new AccountService(accountRepo);
        outContent  = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

    }
    @After
    public void tearDown(){
        inContent = null;
        s = null;

    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testDepositIntoAccount(){
        float balance = 0;
        s = "5";
        inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to deposit" + "\n$5.0 has been deposited.";
        account.depositIntoAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testBadInputDeposit(){
        float balance = 0;
        s = "a\n5";
        inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to deposit" + "\nPlease enter a number" + "\nPlease enter the amount you wish to deposit" + "\n$5.0 has been deposited.";
        account.depositIntoAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void  testWithdrawFromAccount(){
        float balance = 56;
        s = "5";
        inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        //Mockito.when(accountRepo.updateBalance(Mockito.anyFloat()).then());
        float expectedResult = 5;
        float withdrawAmount = account.WithdrawFromAccount(balance);
        Assert.assertEquals(expectedResult, withdrawAmount, 0.00);
    }

    @Test
    public void testBadInputWithdraw(){
        float balance = 56;
        s = "n\n5";
        inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to withdraw" + "\nPlease enter a number" + "\nPlease enter the amount you wish to withdraw";
        float withdrawAmount = account.WithdrawFromAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testOverDraft(){
        float balance = 5;
        s = "10\n5";
        inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to withdraw" + "\nYou do not have enough funds in your account" + "\nPlease enter the amount you wish to withdraw";
        float withdrawAmount = account.WithdrawFromAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testNegativeWithdraw(){
        float balance = 5;
        s = "-10\n5";
        inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to withdraw" + "\nYou can not withdraw a negative amount" + "\nPlease enter the amount you wish to withdraw";
        float withdrawAmount = account.WithdrawFromAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void testNegativeDeposit(){
        float balance = 0;
        s = "-10\n5";
        inContent = new ByteArrayInputStream(s.getBytes());
        System.setIn(inContent);
        String expectedResult = "\nPlease enter the amount you wish to deposit" + "\nYou can not deposit a negative amount" + "\nPlease enter the amount you wish to deposit" + "\n$5.0 has been deposited.";
        account.depositIntoAccount(balance);
        Assert.assertEquals(expectedResult, outContent.toString());
    }

}
