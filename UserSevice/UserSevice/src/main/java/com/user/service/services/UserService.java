package com.user.service.services;

import com.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //getall
    List<User> getAllUser();

    //get single
    User getUser(String userId);

    //update user;
    User updateUser(User user,String userId);

    //delete User
    void deleteUser(String userId);

}
