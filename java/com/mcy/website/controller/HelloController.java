package com.mcy.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@RestController
public class HelloController {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @GetMapping("/admin/test")
    public String admin(){
        return "admin";
    }

    @GetMapping("/db/test")
    public String db(HttpSession session){
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }
        return "db";
    }

    @GetMapping("/hello/test")
    public String hello(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("mcy", "mcy");
        System.out.println(ops.get("mcy"));
        return (String) ops.get("mcy");
    }
}
