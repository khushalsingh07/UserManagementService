package com.webkaps.user.serviceimpl;

import com.webkaps.user.dto.Hotel;
import com.webkaps.user.dto.Rating;
import com.webkaps.user.exception.ResourceNotFoundException;
import com.webkaps.user.external.service.HotelService;
import com.webkaps.user.external.service.RatingService;
import com.webkaps.user.model.User;
import com.webkaps.user.repository.UserRepository;
import com.webkaps.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    HotelService hotelService;

    @Autowired
    RatingService ratingService;

    @Override
    public User saveUser(User user) {
        System.out.println("Inside Save User method");
        User savedUser = userRepository.save(user);
        List<Hotel> hotelList = user.getHotels();
        System.out.println("Hotel List Size  :: "+hotelList.size());
        if (hotelList !=null && hotelList.size()>0) {
            List<Hotel> savedHotelList = hotelList.stream().map(hotel -> {
                System.out.println("he He He " + hotel.toString());
                System.out.println("Saved User Id  :: " + savedUser.getUserId());
                hotel.setUserId(savedUser.getUserId());
                Hotel savedHotel = hotelService.saveHotel(hotel);
                System.out.println("Saved Hotel Id  : " + savedHotel.getHotelId());
                Rating rating = hotel.getRating();
                rating.setHotelId(savedHotel.getHotelId());
                System.out.println("Rating Rating " + rating.toString());
                Rating savedRating = ratingService.saveRating(rating);
                savedHotel.setRating(savedRating);
                return savedHotel;
            }).collect(Collectors.toList());
            savedUser.setHotels(savedHotelList);
        }
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

        //Host & Port is hardcoded and UserId is dynamic
        Hotel[] hotelsAttachedWithUser = restTemplate.getForObject("http://localhost:8083/hotel-api/user/"+user.getUserId(), Hotel[].class);
        List<Hotel> listOfHotels = Arrays.stream(hotelsAttachedWithUser).toList();
        List<Hotel> hotelList = listOfHotels.stream().map(hotel -> {
            //Fetching Rating details on the basis of hotel using feign client
            Rating rating = ratingService.getRatingByHotelId(hotel.getHotelId());
            hotel.setRating(rating);
            return hotel;
        }).collect(Collectors.toList());
        user.setHotels(hotelList);
        return user;
    }
}
