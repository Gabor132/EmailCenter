package User;

import Database.DatabaseHandler;

/**
 *
 * @author Dragos-Alexandru
 */
public class Messages {
    
    private final String subject;
    private final Friends receiver;
    private final String textContent;
    private final String attachmentName;
    private final String attachmentSize;
    private final String timestamp;
    
    public Messages(String subject, String textContent, String timestamp,
            String attachmentName, String attachmentSize, Friends receiver){
        this.subject = subject;
        this.receiver = receiver;
        this.textContent = textContent;
        this.attachmentName = attachmentName;
        this.attachmentSize = attachmentSize;
        this.timestamp = timestamp;
    }

    /* GETTERE */
    public String getSubject() {
        return subject;
    }

    public Friends getReceiver() {
        return receiver;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public String getAttachmentSize() {
        return attachmentSize;
    }
    
    public String getTimestamp(){
        return timestamp;
    }
    
    /* VERIFICARI IN BAZA DE DATE */
    public boolean addMessageToDatabase(){
        return DatabaseHandler.getInstance().addMessageToFriend(this);
    }
    
    public boolean deleteMessageToDatabase(){
        return DatabaseHandler.getInstance().deleteMessageFromFriend(this);
    }

    /* METODE SPECIFICE CLASEI */
    
    @Override
    public String toString() {
        return "Messages{" + "subject=" + subject + ", receiver=" + receiver + ", textContent=" + textContent + ", timestamp=" + timestamp + '}';
    }
}
