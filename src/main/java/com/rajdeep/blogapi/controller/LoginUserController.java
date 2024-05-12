package com.rajdeep.blogapi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/login")
public class LoginUserController {

    @GetMapping("/current_user")
    public String getLoginUserInfo(Principal principal){
        return principal.getName();
    }

}
