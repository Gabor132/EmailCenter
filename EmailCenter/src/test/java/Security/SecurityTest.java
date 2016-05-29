/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;

/**
 *
 * @author Dragos-Alexandru
 */
public class SecurityTest {
    
    List<String> inputList;
    List<String> keyStringList;
    HashMap<String, HashMap<String, String>> expResultEncryptionList;
    HashMap<String, HashMap<String, String>> expResultDecryptionList;
    Random rGenerator;
    
    public SecurityTest() {
        inputList = new ArrayList<>();
        keyStringList = new ArrayList<>();
        expResultEncryptionList = new HashMap<>();
        rGenerator = new Random();
    }

    /**
     * Verifica aleatoriu ca o combinatie de hash-uri sa fie unice
     * Test of hashPassword method, of class Security.
     */
    @org.junit.Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        List<String> hashInputList = new ArrayList<>();
        List<String> expResultList = new ArrayList<>();
        for(int i = 0; i<20; i++){
            int nrChar = rGenerator.nextInt(20)+1;
            String input = "";
            for(int j = 0; j < nrChar; j++){
                input += (char)rGenerator.nextInt(255);
            }
            hashInputList.add(input);
            expResultList.add(Security.hashPassword(input));
        }
        for(int i = 0; i<20; i++){
            String result = Security.hashPassword(hashInputList.get(i));
            assertEquals(expResultList.get(i), result);
        }
    }

    /**
     * Test of encrypt method, of class Security.
     */
    @org.junit.Test
    public void testEncrypt() {
        System.out.println("Test Encrypt");
        
        int nrInput = 100;
        int nrKey = 100;
        
        //Generate input
        for(int i = 0; i < nrInput; i++){
            int nrChar = rGenerator.nextInt(20)+1;
            String input = "";
            for(int j = 0; j < nrChar; j++){
                input += (char)rGenerator.nextInt(255);
            }
            inputList.add(input);
        }
        
        //Generate key strings
        for(int i = 0; i < nrKey; i++){
            int nrChar = rGenerator.nextInt(20)+1;
            String keyString = "";
            for(int j = 0; j < nrChar; j++){
                keyString += (char)rGenerator.nextInt(255);
            }
            keyStringList.add(keyString);
        }
        
        //Generate results
        for(String key:keyStringList){
            HashMap<String, String> keyMap = new HashMap<>();
            for(String input:inputList){
                String result = Security.encrypt(input, key);
                keyMap.put(input, result);
                System.out.println("("+key+" "+input+" = "+result);
            }
            expResultEncryptionList.put(key, keyMap);
        }
        
        //Verify result
        for(String key:keyStringList){
            for(String input:inputList){
                String result = Security.encrypt(input, key);
                String expResult = expResultEncryptionList.get(key).get(input);
                System.out.println("Checking unique " + result);
                assertEquals(" Failed at " + key + " with " + input, expResult, result);
            }
        }
    }

    /**
     * Test of decrypt method, of class Security.
     */
    @org.junit.Test
    public void testDecrypt() {
        System.out.println("decrypt");
        
        //Verify result
        for(String key:keyStringList){
            for(String input:inputList){
                String expResult = input;
                String result = Security.decrypt(expResultEncryptionList.get(key).get(input), key);
                assertEquals(" Failed at " + key + " with " + input, expResult, result);
            }
        }
    }
    
}
