/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Dragos-Alexandru
 */
public class LoginException extends RuntimeException{

    @Override
    public String getMessage() {
        
        String message = "User is not logged in";
        return message;
        
    }

    
}
