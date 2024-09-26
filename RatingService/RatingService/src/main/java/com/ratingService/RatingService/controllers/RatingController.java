package com.ratingService.RatingService.controllers;

import com.ratingService.RatingService.entities.Rating;
import com.ratingService.RatingService.payload.ApiResponse;
import com.ratingService.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1 = ratingService.saveRating(rating);
        return new ResponseEntity<>(rating1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAll(){
        List<Rating> all = ratingService.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId){
        Rating rating = ratingService.getRating(ratingId);
        return new ResponseEntity<>(rating,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserID(@PathVariable String userId){
        List<Rating> ratingsByUserId = ratingService.getRatingsByUserId(userId);
        return new ResponseEntity<>(ratingsByUserId,HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelID(@PathVariable String hotelId){
        List<Rating> ratingsByHotelId = ratingService.getRatingsByHotelId(hotelId);
        return new ResponseEntity<>(ratingsByHotelId,HttpStatus.OK);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating,@PathVariable String ratingId){
        Rating rating1 = ratingService.updateRating(rating, ratingId);
        return new ResponseEntity<>(rating1,HttpStatus.OK);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<?> deleteRating(@PathVariable String ratingId){
        ratingService.deleteRating(ratingId);
        return new ResponseEntity<>(new ApiResponse("Rating deleted SuccessFully !!",true,HttpStatus.OK),HttpStatus.OK);
    }


}
