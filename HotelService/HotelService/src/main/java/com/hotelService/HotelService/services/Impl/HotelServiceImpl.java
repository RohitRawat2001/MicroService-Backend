package com.hotelService.HotelService.services.Impl;

import com.hotelService.HotelService.entities.Hotel;
import com.hotelService.HotelService.exceptions.ResourceNotFoundException;
import com.hotelService.HotelService.repositories.HotelRepository;
import com.hotelService.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        //generate random id
        String string = UUID.randomUUID().toString();
        hotel.setId(string);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel is not found with this id : " + id));
        return hotel;
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String id) {
        Hotel hotel1 = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel is not found with this id : " + id));

        hotel1.setName(hotel.getName());
        hotel1.setAbout(hotel.getAbout());
        hotel1.setLocation(hotel.getLocation());

        Hotel save = hotelRepository.save(hotel1);
        return save;
    }

    @Override
    public void deleteHotel(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel is not found with this id : " + id));
         hotelRepository.delete(hotel);
    }
}
