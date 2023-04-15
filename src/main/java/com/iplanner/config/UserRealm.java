package com.iplanner.config;

import com.iplanner.pojo.User;
import com.iplanner.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserServiceImpl userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        //连接真实数据库
        User user = userService.queryUserByEmail(userToken.getUsername());
        System.out.println("数据库中查询的用户=》"+user);

        if (user==null){
            return null;
        }

        //密码认证shiro做
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
