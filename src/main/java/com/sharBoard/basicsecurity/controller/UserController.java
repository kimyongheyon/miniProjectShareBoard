package com.sharBoard.basicsecurity.controller;

import com.sharBoard.basicsecurity.domain.Account;
import com.sharBoard.basicsecurity.domain.AccountVO;
import com.sharBoard.basicsecurity.service.UserSevice;
import oracle.jdbc.proxy.annotation.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserSevice userSevice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/insertUsers")
    public String insertViewUsers (){

        return "fragments/login/insertUsers";
    }

    @PostMapping("/insertUsers")
    public String insertUsers (AccountVO accountVO){

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountVO, Account.class); //vo있는 정보가 accountEtity로 넘어간다.
        account.setPassword(passwordEncoder.encode(account.getPassword())); //패스워드 암호화;
        userSevice.inserUser(account);

        return "redirect:/";
    }
}
