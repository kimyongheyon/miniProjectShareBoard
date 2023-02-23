package com.sharBoard.basicsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping(value = "/")
    public String  mainPage (){

        return "page/cmm/home";
    }
}
