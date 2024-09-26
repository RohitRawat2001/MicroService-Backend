package com.ratingService.RatingService.services;

import com.ratingService.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating saveRating(Rating rating);

    //get single
    Rating getRating(String ratingId);

    //get all rating
    List<Rating> getAll();

    //get all rating by userId
    List<Rating> getRatingsByUserId(String userId);

    //get all rating by hotedId
    List<Rating> getRatingsByHotelId(String hotelId);

    //update Rating
    Rating updateRating(Rating rating,String ratingId);

    //delete rating
    void deleteRating(String ratingId);
}
