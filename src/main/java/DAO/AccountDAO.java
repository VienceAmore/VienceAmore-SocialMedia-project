package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Account;
import Util.ConnectionUtil;
import java.sql.Statement;

public class AccountDAO {
    //create
    public Account createAccount(Account account){
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO account(username, password) VALUES (?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

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
    public boolean verifyAccount(Account account){
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

            int result = statement.executeUpdate();

            if (result != 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
