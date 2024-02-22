package com.webkaps.user.external.service;

import com.webkaps.user.dto.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/user-rating")
    Rating saveRating(Rating values);

    @GetMapping("/user-rating/hotel/{hotelId}")
    Rating getRatingByHotelId(@PathVariable String hotelId);

    @PutMapping("/user-rating/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/user-rating/{ratingId}")
    void deleteRating(String ratingId);
}
