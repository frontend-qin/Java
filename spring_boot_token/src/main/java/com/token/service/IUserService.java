package com.token.service;

import com.token.entity.User;

public interface IUserService {
    /**
     * 验证用户信息
     * @param account
     * @param password
     * @return
     */
    boolean checkUserInfo(String account, String password);

    /**
     * 返回用户信息
     * @param user
     * @return
     */
    User getUserInfo(User user);
}
