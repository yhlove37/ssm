package com.yuhao.security;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yuhao.pojo.Permission;
import com.yuhao.pojo.Role;
import com.yuhao.pojo.User;
import com.yuhao.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {


//    通过dubbo远程调用用户服务
    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        远程调用用户服务，根据用户查询用户信息
       User user= userService.findByUsername(username);
       if (user==null){
           //用户名不存在
           return  null;
       }
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        Set<Role> roles = user.getRoles();
//       为用户授予角色
        for (Role role : roles) {
           list.add(new SimpleGrantedAuthority(role.getKeyword()));
                 Set<Permission> permissions=  role.getPermissions();
                 //为角色授权
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }

        }
        org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
        return securityUser;


    }
}
