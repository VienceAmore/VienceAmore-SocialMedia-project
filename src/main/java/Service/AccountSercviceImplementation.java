package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountSercviceImplementation implements AccountService {

    AccountDAO accountDAO;

    public AccountSercviceImplementation(){
        this.accountDAO = new AccountDAO();
    }

    @Override
    public Account createAccount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

    @Override
    public Account verifyAccount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyAccount'");
    }

    
}
