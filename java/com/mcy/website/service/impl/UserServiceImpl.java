package com.mcy.website.service.impl;

import com.mcy.website.dao.UserMapper;
import com.mcy.website.entity.User;
import com.mcy.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper = null;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }

    /**
     * 使用redis存储token
     */
    @Override
    public String saveUserLoginInfo(UserDetails principal) {
        return null;
    }

    @Override
    public UserDetails getUserLoginInfo(String username) {
        return null;
    }
}
