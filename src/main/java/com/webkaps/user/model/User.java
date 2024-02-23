package com.webkaps.user.model;

import com.webkaps.user.dto.Hotel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_no")
    private String mobNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "password")
    private String pwd;

    @Column(name = "pwd_reset_status")
    private String pwdResetStatus; // System generated pwd change status

    @Column(name = "user_account_status")
    private String userAccountStatus; // Active/ De-active

    @Column(name = "login_attempt_count")
    private Integer loginAttempt; // No of attempts to be login

    @Column(name = "user_block_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime userBlockTime;

    @Column(name = "user_setup_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "user_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;

    @Column(name = "user_expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expiryDate;

    @Transient
    private List<Hotel> hotels = new ArrayList<>();
}
