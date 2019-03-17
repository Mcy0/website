package com.mcy.website.controller;

import com.mcy.website.entity.ResultDataEnum;
import com.mcy.website.utils.ResultData;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.security.Principal;

@RestController
@RequestMapping("/admin")
@Validated
public class Admin{

    @PostMapping("/message")
    public void message(@NotBlank(message = "{admin.tel}") @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$",
            message = "{admin.tel.format}") String tel,
                              Principal principal) {
        System.out.println(principal.getName());
    }
}
