/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Dragos-Alexandru
 */
public abstract class Security {
    
    public static String hashPassword(String input){
        
        String output = "";
        String salt = "asjchaley1y2y3rkn";
        String pepper = "dajskhd8129hdiuwh189";
        
        output += salt+pepper+input+salt+pepper;
        
        
        try {
            MessageDigest boillingPot = MessageDigest.getInstance("SHA-256");
            byte[] bytes = boillingPot.digest(output.getBytes());
            
            output = "";
            for(byte auxB:bytes){
                output += (char)auxB;
            }
            
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "Failed to hash user password, please contact support", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        
        return output;
    }
    
    public static String encrypt(String input, SecretKey key){
        
        String output = "";
        
        try {
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] encrypted = cipher.doFinal(input.getBytes("UTF8"));
            output = DatatypeConverter.printBase64Binary(encrypted);
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Failed to encrypt user account password", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        
        return output;
    }
    
    public static String decrypt(String input, SecretKey key){
        String output = "";
        try {
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] auxBytes = DatatypeConverter.parseBase64Binary(input);
            byte[] decrypted = cipher.doFinal(auxBytes);
            output = new String(decrypted,"UTF8");
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Failed to decrypt user account password", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        
        return output;
    }
    
}
