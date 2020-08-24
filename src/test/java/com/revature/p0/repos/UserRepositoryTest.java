package com.revature.p0.repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import javax.sql.DataSource;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UserRepositoryTest {

    /*@Mock
    private DataSource ds;
    @Mock
    private Connection conn;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;

    private Optional<AppUser> newUser;

    @Before
    public void setUp() throws Exception {
        assertNotNull(ds);
        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getConnection()).thenReturn(conn);

        newUser = Optional.of(new AppUser());
        newUser.get().setId(1);
        newUser.get().setFirstName("First");
        newUser.get().setLastName("Last");
        newUser.get().setUserName("User");
        newUser.get().setPassword("Pass");
        newUser.get().setRole(Role.ACCOUNT_HOLDER);

        when(rs.first()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn(newUser.get().getFirstName());
        when(rs.getString(3)).thenReturn(newUser.get().getLastName());
        when(rs.getString(4)).thenReturn(newUser.get().getUserName());
        when(rs.getString(5)).thenReturn(newUser.get().getPassword());
        when(rs.getInt(6)).thenReturn(newUser.get().getRole().ordinal());
        when(stmt.executeQuery()).thenReturn(rs);

    }


    @Test
    public void testFindUserByCredentials(){

    }*/
}
