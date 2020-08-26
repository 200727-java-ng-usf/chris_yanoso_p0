package com.revature.p0.services;

import com.revature.p0.exceptions.AuthenticationException;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.AppUser;
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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class UserServiceTest {

    private UserService user;
    private AppUser nullUser;
    private String nullString;
    private UserRepository userRepo = Mockito.mock(UserRepository.class);


    @Before
    public void setUp(){
        user = new UserService(userRepo);
        nullString = "";
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void authenticateNull() throws IOException {
        exceptionRule.expect(InvalidRequestException.class);
        exceptionRule.expectMessage("Invalid username/password provided");
        user.authenticate(nullString,nullString);

    }

    @Test
    public void testIsUserValidNull(){
        boolean expectedResult = false;
        boolean actualResult = user.isUserValid(nullUser);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsUserValid(){
        AppUser newUser = new AppUser("test", "test", "test", "test" );
        boolean expectedResult = true;
        boolean actualResult = user.isUserValid(newUser);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAuthenticateNotFound() throws IOException {
        Mockito.when(userRepo.findUserByCredentials("test", "test")).thenReturn(Optional.empty());
        exceptionRule.expect(AuthenticationException.class);
        exceptionRule.expectMessage("No user found with the provided credentials");
        user.authenticate("test","test");

    }

    @Test
    public void testAuthenticate() throws IOException {
        AppUser expectedUser = new AppUser("test", "test","test","test");
        Mockito.when(userRepo.findUserByCredentials("test", "test")).thenReturn(Optional.of(expectedUser));
        Optional<AppUser> actualUser = user.authenticate("test","test");
        Assert.assertEquals(expectedUser, actualUser.get());

    }

    @Test
    public void testRegisterResourceException() throws IOException{
        AppUser user1 = new AppUser("test", "test","test","test");
        Mockito.when(userRepo.findUserByUserName(user1.getUserName())).thenReturn(Optional.of(user1));
        exceptionRule.expect(ResourcePersistenceException.class);
        exceptionRule.expectMessage("Provided username is already in use!");
        user.register(user1);
    }




}
