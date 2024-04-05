package com.webkaps.user.service;

import com.webkaps.user.model.Login;

public interface LoginService {
    Login validateLoginCredentials(Login login);
}
