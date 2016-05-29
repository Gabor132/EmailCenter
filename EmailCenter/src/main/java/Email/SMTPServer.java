/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import javax.swing.JOptionPane;

/**
 *
 * @author Dragos-Alexandru
 */
public enum SMTPServer {
    GMAIL("smtp.gmail.com",465),YAHOO("smtp.mail.yahoo.com",587);
    String url;
    int port;
    private SMTPServer(String url, int port){
        this.url = url;
        this.port = port;
    }
    
    public static SMTPServer getApropriateSMTPServer(String email){
        SMTPServer apropriateServer = null;
        try{
            String smtpCompany = email.split("@")[1].split(".com")[0];
            switch(smtpCompany.toLowerCase()){
                case "gmail":
                    return GMAIL;
                case "yahoo":
                    return YAHOO;
                default:
                    return null;
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Email address is invalid", "Accounts", JOptionPane.ERROR_MESSAGE);
        }
        return apropriateServer;
    }
}
