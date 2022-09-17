package com.yuhao.dao;

import com.yuhao.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer roleId);
}
