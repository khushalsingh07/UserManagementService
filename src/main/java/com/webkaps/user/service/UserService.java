package com.webkaps.user.service;

import com.webkaps.user.model.User;

import java.util.List;

public interface UserService {

    //Get user by Username
    String getUserByUserName(String userName);

    //Create User
    String saveUser(User user);

    //Get All User
    List<User> getAllUser();

    //Get Single User by given Id
    User getUser(String userId);
}
