package com.hotelService.HotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Hotel is not found on server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}
