package Email;


import User.Accounts;
import java.io.File;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Dragos-Alexandru
 */
public abstract class EmailSender {
    
    /**
     * Method for sending a simple (just text) email
     * @param from
     * @param subject
     * @param to
     * @param content
     * @return true if email was sent successfully and false otherwise
     */
    public static boolean sendMessage(Accounts from, String subject, String to, String content){
        try {
            Email email = new SimpleEmail();
            email.setHostName(from.getSMTPServer().url);
            email.setSmtpPort(from.getSMTPServer().port);
            email.setAuthenticator(new DefaultAuthenticator(from.getEmailAddress(),
                    from.getDecryptedPassword()));
            email.setStartTLSEnabled(true);
            email.setFrom(from.getEmailAddress());
            email.setSubject(subject);
            email.setMsg(content);
            email.addTo(to);
            email.send();
            return true;
        } catch (EmailException ex) {
            JOptionPane.showMessageDialog(null, "Failed "+ex.getMessage(),
                    "Send Email", JOptionPane.ERROR_MESSAGE);
            System.err.println("Failed "+ex.getMessage());
        }
        return false;
    }
    
    /**
     * Method for sending an email with an attachment
     * @param from
     * @param subject
     * @param to
     * @param content
     * @param attachedFile
     * @return true if email was sent successfully and false otherwise
     */
    public static boolean sendMessage(Accounts from, String subject, String to, String content, File attachedFile){
        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(from.getSMTPServer().url);
            email.setSmtpPort(from.getSMTPServer().port);
            email.setAuthenticator(new DefaultAuthenticator(from.getEmailAddress(),
                    from.getDecryptedPassword()));
            email.setStartTLSEnabled(true);
            email.addTo(to);
            email.setFrom(from.getEmailAddress());
            email.setSubject(subject);
            email.setMsg(content);
            email.attach(createEmailAttachment(attachedFile));
            email.send();
            return true;
        } catch (EmailException ex) {
            JOptionPane.showMessageDialog(null, "Failed "+ex.getMessage(),
                    "Send Email", JOptionPane.ERROR_MESSAGE);
            System.err.println("Failed "+ex.getMessage());
        }
        return false;
    }
    
    private static EmailAttachment createEmailAttachment(File toAttach){
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(toAttach.getPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Email attachment");
        attachment.setName(toAttach.getName());
        return attachment;
    }
    
    /**
     * TODO - Implement email fetching function
     * @return only null now
     */
    public static Email getMessages(){
        return null;
    }
}
