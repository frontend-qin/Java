package com.admin.mapper;

import com.admin.entity.User;

public interface UserMapper {
    // 添加一个用户
    void insertUser(User user);
    // 查询一个用户
    User selectUserByAccount(User user);
}
