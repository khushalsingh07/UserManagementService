package com.webkaps.user.serviceimpl;

import com.webkaps.user.exception.ResourceNotFoundException;
import com.webkaps.user.model.User;
import com.webkaps.user.repository.UserRepository;
import com.webkaps.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        System.out.println("Inside Save User method");
        User savedUser = userRepository.save(user);
      return savedUser;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //Get user from database with the help of User Repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return user;
    }
}
