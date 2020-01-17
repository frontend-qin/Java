package com.token.controller;

import com.token.entity.User;
import com.token.entity.UserToken;
import com.token.service.IUserService;
import com.token.utils.ResultUtil;
import com.token.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public ResultUtil<User> login(@RequestBody User user){
        System.out.println(user.toString());
        boolean isPass = this.userService.checkUserInfo(user.getAccount(), user.getPassword());
        if(isPass){
            // 模拟一个返回信息
            UserToken userToken = new UserToken();
            // 设置账号
            userToken.setAccount(user.getAccount());
            userToken.setId(1L);
            // 生成token
            String token = TokenUtil.signToken(user.getAccount(), userToken.getId().toString());
            // 设置token
            userToken.setToken(token);
            return new ResultUtil<User>(userToken);
        }else{
            return new ResultUtil<User>("账号或密码错误");
        }
    }
    @GetMapping("shops")
    public ResultUtil getShops(){
        List<Object> list = new ArrayList();
        list.add("Hello牌毛衣");
        list.add("World牌短袖");
        return new ResultUtil(list);
    }
}
