package com.sharBoard.basicsecurity.controller.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){

        return "fragments/login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       if (authentication != null){
           new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
       }
       return "redirect:/login";
    }
}
