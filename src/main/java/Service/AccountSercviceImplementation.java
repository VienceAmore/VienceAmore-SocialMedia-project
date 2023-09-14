package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountSercviceImplementation implements AccountService {

    AccountDAO accountDAO;

    public AccountSercviceImplementation(){
        this.accountDAO = new AccountDAO();
    }

    @Override
    public Account createAccount(Account account) {
        if(!account.getUsername().isEmpty() && account.getPassword().length() >= 4 )//account.getUsername doesn`t already exist
        {
            return accountDAO.createAccount(account);
        }
        return null;
    }

    @Override
    public Account verifyAccount() {
        // TODO + check
        return null;
    }

    
}
