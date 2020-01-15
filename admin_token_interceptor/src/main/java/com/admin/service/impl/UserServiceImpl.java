package com.admin.service.impl;

import com.admin.entity.User;
import com.admin.mapper.UserMapper;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByAccount(User user) {
        return this.userMapper.selectUserByAccount(user);
    }

    @Override
    public void insertUser(User user) {
        this.userMapper.insertUser(user);
    }
}
