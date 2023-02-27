package com.sharBoard.basicsecurity.service.Impl;

import com.sharBoard.basicsecurity.domain.Account;
import com.sharBoard.basicsecurity.repository.UserRepository;
import com.sharBoard.basicsecurity.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserSevice {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void inserUser(Account account) {

        userRepository.save(account);
    }
}
