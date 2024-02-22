package com.webkaps.user.model;

import com.webkaps.user.dto.Hotel;
import com.webkaps.user.dto.Rating;
import jakarta.persistence.*;
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
@Entity
@Table(name = "user_detail")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_emailId")
    private String userEmailId;
    @Column(name = "about_user")
    private String about;
    /*@Transient
    private List<Rating> ratings = new ArrayList<>();*/

    @Transient
    private List<Hotel> hotels = new ArrayList<>();
}
