/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Database.DatabaseHandler;
import Security.Security;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dragos-Alexandru
 */
public class Accounts {
    
    private final String emailAddress;
    private final String password;
    private final Users user;
    
    private final List<Friends> friends;

    public Accounts(String email, String password, Users user) {
        this.emailAddress = email;
        this.password = password;
        this.user = user;
        this.friends = getFriendsFromDB();
        for(Friends aux:friends){
            System.out.println(aux.getEmailAddress());
        }
    }
    
    public boolean addAccountInDatabase(){
        return DatabaseHandler.getInstance().addAccountToUser(this);
    }
    
    public boolean deleteAccountFromDatabase(){
        return DatabaseHandler.getInstance().deleteAccountFromUser(this);
    }
    
    private List<Friends> getFriendsFromDB(){
        return DatabaseHandler.getInstance().getFriendsForAccount(this);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
    
    public Users getUser(){
        return user;
    }
    
    public void addFriend(Friends toBeAdded){
        friends.add(toBeAdded);
    }
    
    public void deleteFriend(Friends toBeDeleted){
        friends.remove(toBeDeleted);
    }
    
    public List<Friends> getFriends(){
        return friends;
    }
    
    public String getDecryptedPassword(){
        return Security.decrypt(password, user.getUnhasedPassword());
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.emailAddress);
        hash = 71 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Accounts other = (Accounts) obj;
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
}
