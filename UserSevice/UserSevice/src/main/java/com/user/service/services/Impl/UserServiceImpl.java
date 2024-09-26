package com.user.service.services.Impl;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.external.services.RatingService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;///webclient

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // Save user first
        User savedUser = userRepository.save(user);

        /*
        // Fetch existing ratings for the user (if needed)
        List<Rating> ratings = ratingService.getRatingsByUserId(user.getUserId());

        // Create new Rating objects for the saved user
        List<Rating> ratingList = ratings.stream().map(rating -> {
            // Creating a new Rating object and setting the details
            Rating newRating = Rating.builder()
                    .rating(rating.getRating())  // Set the rating value
                    .userId(savedUser.getUserId())  // Assign the newly created user's ID
                    .hotelId(rating.getHotelId())  // Assign the hotel ID
                    .feedback(rating.getFeedback())  // Set the feedback
                    .build();
            // Call the RatingService to create a new Rating
            ratingService.createRating(newRating);  // Assuming this method creates a single rating
            return newRating;
        }).collect(Collectors.toList());

         */

        // You could also return the updated user with the newly created ratings, if necessary
        return savedUser;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //Rest Template
        /*
        //get user from database with the help of repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this Id :" + userId));
        //fetch rating from above user from RATING SERVICE
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/api/ratings/user/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
          //  ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/api/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
          //  logger.info("responsest status code : {}",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return  user;

         */

        //feign client

        // Get user from the database using repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this Id :" + userId));

        // Fetch ratings for this user from Rating Service via FeignClient
        List<Rating> ratings = ratingService.getRatingsByUserId(userId);
        logger.info("Ratings: {}", ratings);

       //  Enhance each Rating object with Hotel information from Hotel Service via FeignClient
        List<Rating> ratingList = ratings.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        // Set the ratings to the user
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(User user, String userId) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this Id :" + userId));

        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAbout(user.getAbout());

        return  userRepository.save(user1);

    }

    @Override
    public void deleteUser(String userId) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this Id :" + userId));
         userRepository.delete(user1);
    }
}
