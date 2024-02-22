package com.webkaps.user.external.service;

import com.webkaps.user.dto.Hotel;
import com.webkaps.user.dto.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @PostMapping("/hotel-api")
    Hotel saveHotel(Hotel hotel);

    @GetMapping("/hotel-service/{hotelId}")
    Hotel getHotel(@PathVariable  String hotelId);
}
