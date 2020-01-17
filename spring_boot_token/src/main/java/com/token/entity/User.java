package com.token.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模拟 一个 user 对象
 */
@Data
@NoArgsConstructor  // 无参构造
@AllArgsConstructor // 有参构造
public class User {
    // 账号id
    private Long id;
    // 用户名
    private String account;
    // 密码
    private String password;
}
