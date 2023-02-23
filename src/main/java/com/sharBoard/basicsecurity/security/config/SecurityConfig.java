package com.sharBoard.basicsecurity.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  /*웹 보안 활성화*/
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    /*패스워드 암호화 하는 부분*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //정적필터들은 보안절차를 받지않고 통과되게 하는 부분
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService);

        /*<인가정책>=============================================================*/
        http
                .authorizeRequests()    /*요청의 의한 보안 검사*/
                .antMatchers("/","/insertUsers").permitAll()
                .anyRequest().authenticated() /*어떤 요청에도 인증을 받도록 설정*/

        /*<인증정책>=============================================================*/
            .and()
                .formLogin() /*form로그인 인증 기능이 작동*/
                ;

        return  http.build();
    }


}
