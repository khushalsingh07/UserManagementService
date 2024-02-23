package com.webkaps.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Id
    @Column(name = "login_id")
    private String loginId;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "login", nullable = false)
    private Date  login;
    @Column(name = "otp", nullable = false)
    private Integer otp;
    @Column(name = "otp_valid_upto", nullable = false)
    private Date otpValidUpTo;

}
