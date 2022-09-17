package com.yuhao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yuhao.dao.PermissionDao;
import com.yuhao.dao.RoleDao;
import com.yuhao.dao.UserDao;
import com.yuhao.pojo.Permission;
import com.yuhao.pojo.Role;
import com.yuhao.pojo.User;
import com.yuhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        User user= userDao.findByUsername(username);
        if (user==null){
            return null;
        }
        Integer id = user.getId();
       Set<Role> roles= roleDao.findRolesByUserID(id);
       if (roles!=null&&roles.size()>0){
           for (Role role : roles) {
               Integer roleId = role.getId();
               Set<Permission> permissions=permissionDao.findPermissionsByRoleId(roleId);
               if (permissions!=null&&permissions.size()>0){
                   role.setPermissions(permissions);
               }
           }
           user.setRoles(roles);
       }
       return user;
    }
}
