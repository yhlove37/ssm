package com.yuhao.dao;

import com.yuhao.pojo.User;

public interface UserDao {
    User findByUsername(String username);
}
