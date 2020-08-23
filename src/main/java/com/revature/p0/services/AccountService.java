package com.revature.p0.services;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;
import com.revature.p0.util.ConnectionFactory;
import com.revature.p0.util.CurrentUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AccountService {

    public static Optional<UserAccount> setCurrentAccount(Optional<AppUser> user) {
       int userId = user.get().getId();
        Optional<UserAccount> _account = Optional.empty();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "Select * from project.user_accounts where user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, CurrentUser.getCurrentUser().get().getId() );


            ResultSet rs = pstmt.executeQuery();

            _account = mapResultSet(rs).stream().findFirst();


        }catch (SQLException se){
            se.printStackTrace();
        }
        return _account;

    }
    private static Set<UserAccount> mapResultSet(ResultSet rs) throws SQLException {
        Set<UserAccount> accounts = new HashSet<>();

        while (rs.next()) {
            UserAccount temp = new UserAccount();
            temp.setId(rs.getInt("id"));
            temp.setBalance(rs.getFloat("balance"));
            temp.setUser_id(rs.getInt("user_id"));
            accounts.add(temp);
        }

        return accounts;
    }
    public static void updateBalance(float balance){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "UPDATE project.user_accounts SET balance = ? where user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1,balance);
            pstmt.setInt(2, CurrentUser.getCurrentUser().get().getId() );


            pstmt.executeUpdate();

        }catch (SQLException se){
            se.printStackTrace();
        }
    }


}



