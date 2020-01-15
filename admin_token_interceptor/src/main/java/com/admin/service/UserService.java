package com.admin.service;

import com.admin.entity.User;

public interface UserService {
    User selectUserByAccount(User user);
    void insertUser(User user);
}
