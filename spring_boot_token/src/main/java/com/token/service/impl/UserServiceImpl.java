package com.token.service.impl;

import com.token.entity.User;
import com.token.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements IUserService {
    @Override
    public boolean checkUserInfo(String account, String password) {
        String username =  "lisi";
        String pwd =  "123456";
        return username.equals(account) && pwd.equals(password) ? true : false;
    }

    @Override
    public User getUserInfo(User user) {
        return null;
    }
}
