package com.hotelService.HotelService.services;

import com.hotelService.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel saveHotel(Hotel hotel);

    //get single hotel
    Hotel getHotel(String id);

    //get all hotels
    List<Hotel> getAll();

    //update hotel
    Hotel updateHotel(Hotel hotel,String id);

    //delete hotel
    void deleteHotel(String id);
}
