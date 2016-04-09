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
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
    
    /**
     * Encrypts input with the keyString given
     * @param input
     * @param keyString
     * @return
     */
    public static String encrypt(String input, String keyString){
        
        //Makes sure the keyString has 16 bytes
        if(keyString.length() > 16){
            keyString = keyString.substring(0, 16);
        }
        //Generates key from user password
        SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), "Blowfish");
        
        String output;
        
        try {
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] encrypted = cipher.doFinal(input.getBytes("UTF8"));
            output = DatatypeConverter.printBase64Binary(encrypted);
            return output;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Failed to encrypt user account password " + keyString + "\n" +
                    ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        return null;
    }
    
    /**
     * Decrypts input with the keyString given
     * @param input
     * @param keyString
     * @return
     */
    public static String decrypt(String input, String keyString){
        
        //Makes sure the keyString has 16 bytes
        if(keyString.length() > 16){
            keyString = keyString.substring(0, 16);
        }
        
        //Generate key from user password
        SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), "Blowfish");
        
        String output;
        try {
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] auxBytes = DatatypeConverter.parseBase64Binary(input);
            byte[] decrypted = cipher.doFinal(auxBytes);
            output = new String(decrypted,"UTF8");
            return output;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Failed to decrypt user account password "+ keyString + "\n" +
                    ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        return null;
    }
    
}
