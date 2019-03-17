package com.mcy.website.controller;

import com.mcy.website.entity.ResultDataEnum;
import com.mcy.website.entity.User;
import com.mcy.website.service.UserService;
import com.mcy.website.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/dba")
public class Dba {

    @Autowired
    private UserService userService = null;

    @PostMapping("/register")
    public ResultData register(@Valid User user){
        User localUser = null;
        try {
            localUser = (User) userService.loadUserByUsername(user.getUsername());
            if (localUser != null){
                return new ResultData(ResultDataEnum.FAILD_3003);//用户已存在
            }
        }catch (Exception e){}
        User addUser = userService.addAdminUser(user);
        return new ResultData(ResultDataEnum.SUCCESS_200, addUser);
    }
}
