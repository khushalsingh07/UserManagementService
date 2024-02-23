package com.webkaps.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login_details")
public class Login {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "login")
    private Date  login;
    @Column(name = "otp")
    private Integer otp;
    @Column(name = "otp_valid_upto")
    private Date otpValidUpTo;

}
