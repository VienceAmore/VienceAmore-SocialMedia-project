package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.AccountDAO;
import Model.Account;
import Util.ConnectionUtil;

public class AccountSercviceImplementation implements AccountService {

    AccountDAO accountDAO;

    public AccountSercviceImplementation(){
        this.accountDAO = new AccountDAO();
    }

    @Override
    public Account createAccount(Account account) {
        if(!account.getUsername().isEmpty() && account.getPassword().length() >= 4 && usernameExist(account))
        {
            return accountDAO.createAccount(account);
        }
        return null;
    }

    @Override
    public Account verifyAccount(Account account) {
        return accountDAO.verifyAccount(account);
    }

    public boolean usernameExist(Account account)
    {
        try{
            Connection connection = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM account WHERE username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, account.getUsername());

            ResultSet result = statement.executeQuery();
            if(!result.next())
                return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
    
}
