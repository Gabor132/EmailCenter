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
