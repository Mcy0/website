package com.mcy.website.config.springSecurity;

import com.mcy.website.utils.ResultData;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFail implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String msg;
        if (e instanceof LockedException){
            msg = "账户被锁定,登录失败!";
        }else if (e instanceof BadCredentialsException){
            msg = "账户或密码输入错误,登录失败!";
        }else {
            msg = "登录失败!";
        }
        ResultData.printWriterJSON(httpServletResponse, 401, 4001, msg, null);
    }
}
