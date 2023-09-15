package Service;

import Model.Account;

public interface AccountService {

    Account createAccount(Account account);

    Account verifyAccount(Account account);
    
}
