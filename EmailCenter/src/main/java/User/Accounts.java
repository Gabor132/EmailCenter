/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Database.DatabaseHandler;
import Security.Security;
import java.util.Objects;

/**
 *
 * @author Dragos-Alexandru
 */
public class Accounts {
    
    private final String email;
    private final String password;
    private final User user;

    public Accounts(String email, String password, User user) {
        this.email = email;
        this.password = password;
        this.user = user;
    }
    
    public boolean addAccountInDatabase(User user){
        return DatabaseHandler.getInstance().addAccountToUser(this, user);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDecryptedPassword(){
        return Security.decrypt(user.getUnhasedPassword(), password);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.email);
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
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
}
