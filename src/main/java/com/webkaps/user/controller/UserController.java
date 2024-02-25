package com.webkaps.user.controller;

import com.webkaps.user.model.User;
import com.webkaps.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/checkUserNameAvailability/{userName}")
    public ResponseEntity<String> getUserByUserName(@PathVariable String userName){
        String userStatus = userService.getUserByUserName(userName);
        return  ResponseEntity.ok(userStatus);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        String userStatus = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userStatus);
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
