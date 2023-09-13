package Service;

import Model.Account;

public class AccountSercviceImplementation implements AccountService {

    AccountDAO accountDAO;

    public AccountSercviceImplementation() {
        this.accountDAO = new AccountDAOmySQLImplementation();


        public Account createAccount(){
            return null;
        }
    }

    
}
