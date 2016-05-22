/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Database.DatabaseHandler;
import Exceptions.LoginException;
import Security.Security;
import java.util.List;

/**
 *
 * @author Dragos-Alexandru
 */
public final class Users {
    
    private List<Accounts> accounts;
    
    private final String username;
    private final String password;
    private final String unhasedPassword;
    
    private boolean loggedIn = false;

    public Users(String username, String password) {
        this.username = username;
        this.unhasedPassword = password;
        this.password = Security.hashPassword(password);
        accounts = getAccountsFromDB();
    }

    /* GETTERE */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUnhasedPassword(){
        return unhasedPassword;
    }
    
    /* VERIFICARI IN BAZA DE DATE */
    public boolean checkAvailable(){
        return DatabaseHandler.getInstance().checkUserForLogin(this);
    }
    
    public boolean registerUser(){
        return DatabaseHandler.getInstance().registerUser(this);
    }
    
    /* MANIPULARE ACCOUNTS */
    public void addAccount(Accounts toBeAdded){
        accounts.add(toBeAdded);
    }
    
    public void deleteAccount(Accounts toBeDeleted){
        accounts.remove(toBeDeleted);
    }
    
    public List<Accounts> getAccounts() throws LoginException{
        return accounts;
    }
    
    private List<Accounts> getAccountsFromDB(){
        return DatabaseHandler.getInstance().getAccountsForUser(this);
    }
    
    /* METODE SPECIFICE CLASEI */
    public void setLoggedIn(boolean value){
        loggedIn = value;
    }
    
    public boolean isLoggedIn(){
        return loggedIn;
    }

    @Override
    public String toString() {
        return "Users{" + "accounts=" + accounts + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
