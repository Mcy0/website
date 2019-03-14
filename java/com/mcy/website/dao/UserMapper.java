package com.mcy.website.dao;

import com.mcy.website.entity.Role;
import com.mcy.website.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    //根据用户名(username)获取用户
    User loadUserByUsername(String username);
    //根据用户id获取用户角色信息
    List<Role> getUserRolesByUid(Integer id);
}
