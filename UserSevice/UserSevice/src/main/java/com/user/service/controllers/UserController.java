package com.user.service.controllers;

import com.user.service.entities.User;
import com.user.service.payload.ApiResponse;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    public Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        String string = UUID.randomUUID().toString();
        user.setUserId(string);
        User user1 = userService.saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){
        List<User> allUser = userService.getAllUser();
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }

    int recount = 1;
    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        logger.info("Get Single User Handler : UserController");
        logger.info("Retry count : {}" ,recount);
        recount++;
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //create a fallback method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(@PathVariable String userId, Exception ex){
         logger.info("Fallback is executed because service is down : "+ex.getMessage());
        User user = User.builder().name("dummy").email("dummy@gmail.com").about("This user is created because some service is down").userId("123").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable String userId){
        User user1 = userService.updateUser(user, userId);
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully",true,HttpStatus.OK),HttpStatus.OK);
    }
}
