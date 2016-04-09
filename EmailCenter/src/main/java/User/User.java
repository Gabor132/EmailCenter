/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Exceptions.LoginException;
import Security.Security;
import java.util.List;

/**
 *
 * @author Dragos-Alexandru
 */
public final class User {
    
    private List<Accounts> accounts;
    
    private final String username;
    private final String password;
    

    public User(String username, String password) {
        this.username = username;
        this.password = Security.hashPassword(password);
    }

    public List<Accounts> getAccounts() throws LoginException{
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }
    
    
    
}
