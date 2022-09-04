package com.yuhao.security;


import com.yuhao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements UserDetailsService {

//    调用BCrypt加密
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//  模拟数据库中的用户数据
    public Map<String ,User> map=new HashMap<String, User>() ;

    public void initData(){
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(bCryptPasswordEncoder.encode("admin"));

        User user2 =new User();
        user2.setUsername("yuhao");
        user2.setPassword(bCryptPasswordEncoder.encode("123456"));

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }



    /**
     *根据用户加载用户信息
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("username"+userName);
//        初始化数据（模拟数据库）
        initData();
//        从map中取出用户
        User user = map.get(userName);
        if (user==null)
        {
            return  null;
        }
//模拟数据库中密码，后期需要查询数据库
        String passWordInDb=user.getPassword();
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//授权，后期需要改为查询数据库动态的获取用户所拥有的权限
//        list.add(new SimpleGrantedAuthority("add"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        UserDetails userSecurity=new org.springframework.security.core.userdetails.User(userName,passWordInDb,list);
        return userSecurity;
    }
}
