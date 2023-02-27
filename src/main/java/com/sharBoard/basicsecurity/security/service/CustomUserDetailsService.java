package com.sharBoard.basicsecurity.security.service;

import com.sharBoard.basicsecurity.domain.Account;
import com.sharBoard.basicsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Account account = userRepository.findByUsername(username);

       if (account == null ){
           throw new UsernameNotFoundException("UsernameNotFoundException");  /*널이면 에러를 발생시키는 부분*/
       }

       List<GrantedAuthority> roles = new ArrayList<>();
       roles.add(new SimpleGrantedAuthority(account.getRole()));

       AccountContext accountContext = new AccountContext(account,roles);

        return accountContext;
    }
}
