package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    //create
    public Account createAccount(Account account){
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO account(username, password) VALUES (?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            //statement.setInt(1, account.getAccount_id());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                return new Account(keys.getInt(1), account.getUsername(), account.getPassword());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //read
    public void verifyAccount(){

    }

    //update 

    //delete
}
