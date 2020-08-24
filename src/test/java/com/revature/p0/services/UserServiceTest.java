package com.revature.p0.services;

import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.models.AppUser;
import com.revature.p0.repos.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.revature.p0.exceptions.InvalidRequestException;
import org.mockito.Mock;


import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class UserServiceTest {
    
    private UserRepository userRepo;
    private UserService user;
    private AppUser nullUser;
    private String nullString;
    @Mock
    private DataSource ds;
    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;
    @Before
    public void setUp(){
        userRepo = new UserRepository();
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


}
