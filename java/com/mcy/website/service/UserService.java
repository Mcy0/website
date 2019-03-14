package com.mcy.website.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    String saveUserLoginInfo(UserDetails principal);

    UserDetails getUserLoginInfo(String username);
}
