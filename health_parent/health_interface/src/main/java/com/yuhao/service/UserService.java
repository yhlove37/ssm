package com.yuhao.service;

import com.yuhao.pojo.User;

public interface UserService {
    User findByUsername(String username);
}
