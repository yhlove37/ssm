package com.yuhao.dao;

import com.yuhao.pojo.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findRolesByUserID(Integer id);
}
