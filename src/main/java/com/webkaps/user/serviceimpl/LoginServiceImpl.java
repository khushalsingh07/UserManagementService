package com.webkaps.user.serviceimpl;

import com.webkaps.user.model.Login;
import com.webkaps.user.repository.LoginRepository;
import com.webkaps.user.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LoginRepository loginRepository;

    @Override
    public Login validateLoginCredentials(Login login) {
        return null;
    }
}
