package com.webkaps.user.serviceimpl;

import com.webkaps.user.dto.Hotel;
import com.webkaps.user.dto.Rating;
import com.webkaps.user.exception.ResourceNotFoundException;
import com.webkaps.user.model.User;
import com.webkaps.user.repository.UserRepository;
import com.webkaps.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

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
        //Host & Port is hardcoded and User Id is dynamic
        Rating[] ratingByUser = restTemplate.getForObject("http://localhost:8082/user-rating/user/"+user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingByUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
           //Fetching Hotel Details on the basis of Rating
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8083/hotel-service/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
