package com.mcy.website.dao;

import com.mcy.website.entity.LoginIp;
import com.mcy.website.entity.Role;
import com.mcy.website.entity.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserMapper {
    //根据用户名(username)获取用户
    User loadUserByUsername(String username);
    //根据用户id获取用户角色信息
    List<Role> getUserRolesByUid(Integer id);
    //超级管理员添加管理员
    int addUser(User user);
    //通过角色名获取角色对象
    Role getRoleByRoleName(String name);
    //设置用户角色信息
    int setRoleByUserId(@Param("uid") Integer uid, @Param("rid") Integer rid);
    //添加登录ip,和登录时间
    int addLoginIp(@Param("uid")int uid, @Param("ip") String ip, @Param("loginDate") Date loginDate);
    //获取登录ip
    List<LoginIp> getLoginIps(int id);
}
