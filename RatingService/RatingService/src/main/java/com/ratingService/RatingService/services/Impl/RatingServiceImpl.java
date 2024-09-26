package com.ratingService.RatingService.services.Impl;

import com.ratingService.RatingService.entities.Rating;
import com.ratingService.RatingService.exceptions.ResourceNotFoundException;
import com.ratingService.RatingService.repositories.RatingRespository;
import com.ratingService.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRespository ratingRespository;


    @Override
    public Rating saveRating(Rating rating) {
        return ratingRespository.save(rating);
    }

    @Override
    public Rating getRating(String ratingId) {
        Rating rating = ratingRespository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating is not found with this Id : " + ratingId));
        return rating;
    }

    @Override
    public List<Rating> getAll() {
       return ratingRespository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRespository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRespository.findByHotelId(hotelId);
    }

    @Override
    public Rating updateRating(Rating rating, String ratingId) {
        Rating rating1 = ratingRespository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating is not found with this Id : " + ratingId));
        rating1.setRating(rating.getRating());
        rating1.setFeedback(rating.getFeedback());
        Rating save = ratingRespository.save(rating1);
        return  save;
    }

    @Override
    public void deleteRating(String ratingId) {
        Rating rating1 = ratingRespository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating is not found with this Id : " + ratingId));
        ratingRespository.delete(rating1);
    }
}
