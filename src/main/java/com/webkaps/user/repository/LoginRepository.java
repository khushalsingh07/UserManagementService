package com.webkaps.user.repository;

import com.webkaps.user.model.Login;
import com.webkaps.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, String> {

    @Query("SELECT u FROM User u WHERE u.userName= :userName AND u.pwd= :pwd")
    User findUserByUserNameAndPwd(@Param("userName") String userName, @Param("pwd") String pwd);
}
