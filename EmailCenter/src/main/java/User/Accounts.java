package User;

import Database.DatabaseHandler;
import Email.SMTPServer;
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
    
    private final SMTPServer smtpServer;
    
    private final List<Friends> friends;

    public Accounts(String email, String password, Users user) {
        this.emailAddress = email;
        this.password = password;
        this.user = user;
        this.friends = getFriendsFromDatabase();
        this.smtpServer = SMTPServer.getApropriateSMTPServer(email);
    }

    /* GETTERE */
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
    
    public Users getUser(){
        return user;
    }
    
    public SMTPServer getSMTPServer(){
        return smtpServer;
    }
    
    /* VERIFICARI IN BAZA DE DATE */
    public boolean addAccountInDatabase(){
        return DatabaseHandler.getInstance(true, false).addAccountToUser(this);
    }
    
    public boolean deleteAccountFromDatabase(){
        return DatabaseHandler.getInstance(true, false).deleteAccountFromUser(this);
    }
    
    /* MANIPULARE FRIENDS */
    public void addFriend(Friends toBeAdded){
        friends.add(toBeAdded);
    }
    
    public void deleteFriend(Friends toBeDeleted){
        friends.remove(toBeDeleted);
    }
    
    public List<Friends> getFriends(){
        return friends;
    }
    
    private List<Friends> getFriendsFromDatabase(){
        return DatabaseHandler.getInstance(true, false).getFriendsForAccount(this);
    }
    
    /* METODE SPECIFICE CLASEI */
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
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "Accounts{" + "emailAddress=" + emailAddress + ", password=" + password + ", user=" + user + ", friends=" + friends + '}';
    }
    
    
    
}
