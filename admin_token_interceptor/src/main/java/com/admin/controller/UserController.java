package com.admin.controller;


import com.admin.entity.Token;
import com.admin.entity.User;

import com.admin.service.UserService;
import com.admin.utils.JwtToken;
import com.admin.utils.Result;
import com.admin.utils.SHAEncrypt;
import com.admin.utils.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtToken jwtToken;

    @PostMapping("login")
    public Result<Object> login(@RequestBody User user) throws NoSuchAlgorithmException {
        // 1. 去数据库 匹配账号和密码
        User account = this.userService.selectUserByAccount(user);
        // 获取加密的密码
        String shaStr = SHAEncrypt.getSHAStr(user.getPassword());

        // 比较数据库查询的密码和输入的密码是否 一样
        if(shaStr.equals(account.getPassword())){
            account.setPassword(null);
            // 生成token
            String token = jwtToken.generateToken(account.getId(), account.getAccount());
            System.out.println(token);
            Token.saveToken = token;
            // 创.建新的tokenUser对象
            Token utoken = new Token();
            utoken.setId(account.getId());
            utoken.setAccount(account.getAccount());
            utoken.setToken(token);
            utoken.setType(account.getType());
            return new Result<Object>(StatusCode.OK,"登录成功", utoken);
        }
        return new Result<Object>(StatusCode.ERROR,"账号或密码错误", "");
    }
    /**
     * 创建一个账户
     * @param user
     * @return
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("insert")
    public Result<String> createUser(@RequestBody User user) throws NoSuchAlgorithmException {
        // 先去数据库查找有木有该账户
        User account = this.userService.selectUserByAccount(user);
        // 如果为null , 说明不存在
        if(account == null){
            // 对密码进行加密
            String pwd = SHAEncrypt.getSHAStr(user.getPassword());
            user.setPassword(pwd);
            System.out.println(user.toString());
            this.userService.insertUser(user);
            return new Result<String>(StatusCode.OK, "创建成功", "");
        }
        return new Result<String>(StatusCode.ERROR, "该用户已经存在", "");
    }
}
