package com.revature.p0.repos;

import com.revature.p0.db.UserDB;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.Role;
import com.revature.p0.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRepository {
    /**
     * Constructs user database
     * has methods to find users by credentials/username
     * can save new users to database
     */

    private UserDB userDataSet = UserDB.userDataSet;
    private String baseQuery = "SELECT * FROM project.app_users au " +
                               "JOIN project.user_roles ur " +
                               "ON au.role_id = ur.id ";

    public UserRepository(){
        super();
    }



    public Optional<AppUser> findUserByCredentials(String userName, String password) {
       // return userDataSet.findUserByCredentials(userName, password);
        Optional<AppUser> _user = Optional.empty();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = baseQuery + "WHERE username = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            _user = mapResultSet(rs).stream().findFirst();


        }catch (SQLException se){
            se.printStackTrace();
        }
        // when the role is returned from database, it is off by 1!
        return _user;
    }



    public Optional<AppUser> findUserByUserName(String userName){
       // return userDataSet.findUserByUserName(userName);
        Optional<AppUser> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {


            String sql = baseQuery + "WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return _user;
    }

    public void save(AppUser newUser) throws IOException {
        //userDataSet.addNewUserToFile(newUser);
        //return userDataSet.addUser(newUser);
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project.app_users (username, password, first_name, last_name, role_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
            pstmt.setString(1, newUser.getUserName());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());
            pstmt.setInt(5, newUser.getRole().ordinal() + 1);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {

                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                newUser.setId(rs.getInt(1));

            }

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    private Set<AppUser> mapResultSet(ResultSet rs) throws SQLException {
        Set<AppUser> users = new HashSet<>();

        while (rs.next()) {
            AppUser temp = new AppUser();
            temp.setId(rs.getInt("id"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("last_name"));
            temp.setUserName(rs.getString("username"));
            temp.setPassword(rs.getString("password"));
            temp.setRole(Role.getByName(rs.getString("name")));
            users.add(temp);
        }

        return users;
    }
}
