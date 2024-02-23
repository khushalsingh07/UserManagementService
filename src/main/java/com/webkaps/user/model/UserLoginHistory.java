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
@Table(name = "login_history")
public class UserLoginHistory {
    @Id
    @Column(name = "login_history_id")
    private String loginHistoryId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "login")
    private Date login;
    @Column(name = "logout")
    private Date logout;
}
