package com.sharBoard.basicsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("loginPage")
    public String loginPage(){
        return "loginPage";
    }

    @GetMapping("expired")
    public String expired() {return "세션이 만료 됨";}
}
