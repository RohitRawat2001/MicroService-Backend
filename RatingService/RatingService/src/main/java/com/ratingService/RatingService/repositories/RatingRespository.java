package com.ratingService.RatingService.repositories;

import com.ratingService.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRespository extends MongoRepository<Rating,String> {

    //custom finder methods
    List<Rating>  findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
