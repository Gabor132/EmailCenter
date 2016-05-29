package User;

import Database.DatabaseHandler;
import java.util.List;

/**
 *
 * @author Dragos-Alexandru
 */
public class Friends {
    
    private final String emailAddress;
    private final Accounts account;
    private final List<Messages> messages;
    
    public Friends(String email, Accounts account){
        this.emailAddress = email;
        this.account = account;
        this.messages = getMessagesFromDatabase();
        for(Messages aux:messages){
            System.out.println(aux);
        }
    }

    /* GETTERE */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public Accounts getAccount(){
        return account;
    }
    
    /* VERIFICARI IN BAZA DE DATE */
    public boolean addFriendInDatabase(){
        return DatabaseHandler.getInstance(true, false).addFriendToAccount(this);
    }
    
    public boolean deleteFriendFromDatabase(){
        return DatabaseHandler.getInstance(true, false).deleteFriendFromAccount(this);
    }
    
    /* MANIPULARE MESSAGES */
    public void addMessage(Messages toBeAdded){
        this.messages.add(toBeAdded);
    }
    
    public void deleteMessage(Messages toBeDeleted){
        this.messages.remove(toBeDeleted);
    }
    
    public List<Messages> getMessages(){
        return messages;
    }
    
    private List<Messages> getMessagesFromDatabase(){
        return DatabaseHandler.getInstance(true, false).getMessageFromFriend(this);
    }
    
    /* METODE SPECIFICE CLASEI */

    @Override
    public String toString() {
        return "Friends{" + "emailAddress=" + emailAddress + ", account=" + account + '}';
    }
}
