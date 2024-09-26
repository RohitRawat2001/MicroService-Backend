package com.user.service.external.services;

import com.user.service.entities.Rating;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/api/ratings/")
    public Rating createRating(Rating rating);

    @GetMapping("/api/ratings/user/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable String userId);

    @GetMapping("/api/ratings/{ratingId}")
    Rating getSingleRating(@PathVariable String ratingId);
}
