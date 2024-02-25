package com.webkaps.user.repository;

import com.webkaps.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.userName= :userName")
    User findUserByUserName(@Param("userName") String userName);

    @Query("SELECT u FROM User u WHERE u.mobNo= :mobNo OR u.emailId= :emailId")
    User findUserByMobileAndEmaild(@Param("mobNo") String mobNo, @Param("emailId") String emailId);
}
