package com.webkaps.user.service;

import com.webkaps.user.model.User;

import java.util.List;

public interface UserService {
    //Create User
    User saveUser(User user);

    //Get All User
    List<User> getAllUser();

    //Get Single User by given Id
    User getUser(String userId);
}
