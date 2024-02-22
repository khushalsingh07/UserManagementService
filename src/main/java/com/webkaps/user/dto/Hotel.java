package com.webkaps.user.dto;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    private String hotelId;
    private String name;
    private String location;
    private String about;
    private String userId;
    @Transient
    Rating rating;
}
