package com.iplanner.controller;

import com.iplanner.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable("username") String username, @PathVariable("password") String password){
        System.out.println(username+password);
        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 执行登录的方法
        try {
            subject.login( token );
            return "login";
        } catch ( UnknownAccountException uae ) {
            return "error";
        } catch ( IncorrectCredentialsException ice ) {
            return "error";
        }
    }



}
