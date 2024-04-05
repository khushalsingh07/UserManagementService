package com.webkaps.user.controller;

import com.webkaps.user.model.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    public ResponseEntity<Login> validateUserLoginStatus(Login loginCredentials){

         return null;
     }
}
