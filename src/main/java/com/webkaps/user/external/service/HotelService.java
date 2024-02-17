package com.webkaps.user.external.service;

import com.webkaps.user.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotel-service/{hotelId}")
    Hotel getHotel(@PathVariable  String hotelId);
}
