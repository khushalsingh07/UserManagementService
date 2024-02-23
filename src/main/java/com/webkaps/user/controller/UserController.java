package com.webkaps.user.controller;

import com.webkaps.user.model.User;
import com.webkaps.user.service.UserService;
import com.webkaps.user.util.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserUtility userUtility;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        //Need to check the duplicity of Mobile no and email id in database
        String randomNo = UUID.randomUUID().toString();
        user.setUserId(randomNo);
        user.setPwd(userUtility.getUserPwd()); // Need to set dynamic password with specific criteria
        user.setPwdResetStatus("N");
        user.setUserAccountStatus("A");
        user.setLoginAttempt(0);
        LocalDateTime currentDateWithTime = LocalDateTime.now();
        user.setCreateDate(currentDateWithTime);
        user.setUpdateDate(currentDateWithTime);
        user.setExpiryDate(currentDateWithTime.plusDays(30)); // Need to work here for setting Max time of the day
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return  ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers = userService.getAllUser();
        return ResponseEntity.ok(allUsers);
    }
}
