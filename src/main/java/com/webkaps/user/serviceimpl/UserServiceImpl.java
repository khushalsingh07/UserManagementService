package com.webkaps.user.serviceimpl;

import com.webkaps.user.dto.Hotel;
import com.webkaps.user.dto.Rating;
import com.webkaps.user.exception.ResourceNotFoundException;
import com.webkaps.user.external.service.HotelService;
import com.webkaps.user.external.service.RatingService;
import com.webkaps.user.model.User;
import com.webkaps.user.repository.UserRepository;
import com.webkaps.user.service.UserService;
import com.webkaps.user.util.UserUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, HotelService hotelService, RatingService ratingService, UserUtility userUtility) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
        this.ratingService = ratingService;
        this.userUtility = userUtility;
    }

    UserRepository userRepository;

    RestTemplate restTemplate;

    HotelService hotelService;

    RatingService ratingService;

    private final UserUtility userUtility;

    @Override
    public String getUserByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        if (user!=null)
            return "A";
        else
            return "NA";
    }

    @Override
    public String saveUser(User user) {
        System.out.println("Inside Save User method");
        String userStatus = validateUserByMobileAndEmailId(user);
        if (userStatus==null){
            String randomNo = UUID.randomUUID().toString();
            user.setUserId(randomNo);
            user.setPwd(userUtility.getUserPwd());
            user.setPwdResetStatus("N");
            user.setUserAccountStatus("A");
            user.setLoginAttempt(0);
            LocalDateTime currentDateWithTime = LocalDateTime.now();
            user.setCreateDate(currentDateWithTime);
            user.setUpdateDate(currentDateWithTime);
            user.setExpiryDate(currentDateWithTime.plusDays(30));
            User savedUser = userRepository.save(user);
            if (savedUser!=null){
                userStatus="S";
                List<Hotel> hotelList = user.getHotels();
                if (hotelList !=null && hotelList.size()>0) {
                    List<Hotel> savedHotelList = hotelList.stream().map(hotel -> {
                        hotel.setUserId(savedUser.getUserId());
                        Hotel savedHotel = hotelService.saveHotel(hotel);
                        Rating rating = hotel.getRating();
                        rating.setHotelId(savedHotel.getHotelId());
                        Rating savedRating = ratingService.saveRating(rating);

                        //Below is the old work when sending the response in a way of
                        // user Object. Now there is no requirement
                        savedHotel.setRating(savedRating);
                        return savedHotel;
                    }).collect(Collectors.toList());
                    savedUser.setHotels(savedHotelList);
            }
          }
        }
        return userStatus;
    }

    private String validateUserByMobileAndEmailId(User user) {
        String status = null;
        User userByMobileAndEmailId = userRepository.findUserByMobileAndEmaild(user.getMobNo(), user.getEmailId());
        if(userByMobileAndEmailId!=null) {
            if (userByMobileAndEmailId.getMobNo()!=null && userByMobileAndEmailId.getMobNo().equalsIgnoreCase(user.getMobNo())
                && userByMobileAndEmailId.getEmailId()!=null && userByMobileAndEmailId.getEmailId().equalsIgnoreCase(user.getEmailId())){
                status = "FB";
            } else if (userByMobileAndEmailId.getMobNo()!=null && userByMobileAndEmailId.getMobNo().equalsIgnoreCase(user.getMobNo())) {
                status = "FM";
            } else if (userByMobileAndEmailId.getEmailId()!=null && userByMobileAndEmailId.getEmailId().equalsIgnoreCase(user.getEmailId())) {
                status = "FE";
            }
            System.out.println("STATUS  :: " + status);
        }
        return status;
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
