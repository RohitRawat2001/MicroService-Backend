package com.hotelService.HotelService.controllers;

import com.hotelService.HotelService.entities.Hotel;
import com.hotelService.HotelService.payload.ApiResponse;
import com.hotelService.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.saveHotel(hotel);
        return new ResponseEntity<>(hotel1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAll(){
        List<Hotel> all = hotelService.getAll();
        return  new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id){
        Hotel hotel = hotelService.getHotel(id);
        return  new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String id){
        Hotel hotel1 = hotelService.updateHotel(hotel, id);
        return  new ResponseEntity<>(hotel1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable String id){
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(new ApiResponse("Hotel is deleted SuccessFully !!",true,HttpStatus.OK),HttpStatus.OK);
    }
}
