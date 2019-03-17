package com.mcy.website.service.impl;

import com.mcy.website.dao.UserMapper;
import com.mcy.website.entity.Role;
import com.mcy.website.entity.User;
import com.mcy.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper = null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public User addAdminUser(User user) {
        //设置一些注册管理员角色信息
        user.setPassword(user.getPassword());
        Date creatAt = new Date();
        user.setCreatedAt(creatAt);
        //添加角色,主键回退
        userMapper.addUser(user);
        //获取管理员角色信息
        Role role = userMapper.getRoleByRoleName("ROLE_admin");
        //设置管理员角色
        userMapper.setRoleByUserId(user.getId(), role.getId());
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addLoginIp(Integer id, String ip, Date loginDate) {
        return userMapper.addLoginIp(id, ip, loginDate);
    }
}
