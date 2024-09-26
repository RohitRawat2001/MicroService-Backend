package com.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("User not found on server");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
