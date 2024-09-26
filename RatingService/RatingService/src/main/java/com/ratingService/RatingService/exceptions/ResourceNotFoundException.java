package com.ratingService.RatingService.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Rating is not found on server!!");
    }

    public  ResourceNotFoundException(String message){
        super(message);
    }

}
