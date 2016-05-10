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
    private final String unhasedPassword;
    
    private boolean loggedIn = false;

    public User(String username, String password) {
        this.username = username;
        this.unhasedPassword = password;
        this.password = Security.hashPassword(password);
        accounts = getAccountsFromDB();
        for(Accounts aux:accounts){
            System.out.println(aux.getEmail()+" "+aux.getPassword());
        }
    }
    
    public boolean checkAvailable(){
        return Database.DatabaseHandler.getInstance().checkUserForLogin(this);
    }
    
    public boolean registerUser(){
        return Database.DatabaseHandler.getInstance().registerUser(this);
    }
    
    public void addAccount(Accounts account){
        accounts.add(account);
    }
    
    private List<Accounts> getAccountsFromDB(){
        return Database.DatabaseHandler.getInstance().getAccountsForUser(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUnhasedPassword(){
        return unhasedPassword;
    }
    
    public List<Accounts> getAccounts() throws LoginException{
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }
    
    public void setLoggedIn(boolean value){
        loggedIn = value;
    }
    
    public boolean isLoggedIn(){
        return loggedIn;
    }
}
