package com.mcy.website.service;

import com.mcy.website.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;

public interface UserService extends UserDetailsService {
    /**
     * 超级管理员添加管理员
     * @param user 管理员
     * @return 管理员
     */
    User addAdminUser(User user);

    /**
     *  用户没测登录都添加对应ip
     * @param id 用户id
     * @param ip 登录ip
     * @return 改变数
     */
    int addLoginIp(Integer id, String ip, Date loginDate);
}
