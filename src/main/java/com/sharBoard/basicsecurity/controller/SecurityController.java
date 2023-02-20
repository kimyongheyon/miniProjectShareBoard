package com.sharBoard.basicsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    /**
     * 로그인 페이지 url
     * @return
     */
    @GetMapping("loginPage")
    public String loginPage(){
        return "loginPage";
    }

    /**
     * 유저만 들어갈 수 있는 페이지
     * @return
     */
    @GetMapping("/user")
    public String user(){return "user";}

    /**
     * admin만 들어갈 수 있는 페이지
     * @return
     */
    @GetMapping("/admin/pay")
    public String adminPay(){return "adminPay";}

    /**
     * admin과 sys만 들어갈 수 있는 페이지
     * @return
     */
    @GetMapping("/admin/**")
    public String admin(){return "admin";}

/*    @GetMapping("expired")
    public String expired() {return "세션이 만료 됨";}*/
}
