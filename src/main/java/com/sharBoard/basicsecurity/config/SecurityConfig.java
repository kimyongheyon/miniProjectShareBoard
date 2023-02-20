package com.sharBoard.basicsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity  /*웹 보안 활성화*/
public class SecurityConfig {

/*    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService();
    }*/
//    @Autowired
//  private UserDetailsService userDetailsService;


    AuthenticationManager auth;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);

        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER") ;/*유저 아이디를 생성하는 부분  {noop}은 뒤에서 다시 공부*/
        auth.inMemoryAuthentication().withUser("sys").password("{noop}1234").roles("SYS","USER"); /*이런식으로 넣으면 다른 권한에도 접근할 수 있다*/
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","SYS","USER");
        /*<인가정책>=============================================================*/
        http
                .authorizeRequests()    /*요청의 의한 보안 검사*/
                .antMatchers("/user").hasRole("USER")  /* /user로 접근하면 USER라는 권한을 심사한다는 의미*/
                .antMatchers("/admin/pay").hasRole("ADMIN")
                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
                .anyRequest().authenticated(); /*어떤 요청에도 인증을 받도록 설정*/

        /*<인증정책>=============================================================*/
        http
                .formLogin() /*form로그인 인증 기능이 작동*/
                /*.loginPage("/loginPage")    로그인 페이지 설정하는 부분*/
                .defaultSuccessUrl("/")     /*인증 성공시에 이동할 url*/
                .failureUrl("/login")            /*인증 실패시에 이동할 url*/
                .usernameParameter("userId")      /*파라미터 name 값을 설정  시큐리티에서 제공하는 login페이지 말고 직접 만들시에 name값을 여기 지정한 거랑 똑같이 맞추기*/
                .passwordParameter("passwd")      /*파라미터 name 값을 설정*/
                .loginProcessingUrl("/login_proc")   /*로그인할 때 form 태그에 action 경로를 설정해줌*/
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                 System.out.println("authenticatuin:" + authentication.getName());    /*사용자 이름을 찍어보는 부분*/
                                 response.sendRedirect("/")  /*성공을 하면 / 페이지로 이동하는 부분*/
                                 ;
                    }
                })/*로그인 성공했을때 호출할 핸들러*/
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                             System.out.println("exception:"+ exception.getMessage());   /*실패 메세지 띄우는 부분*/
                             response.sendRedirect("/login");      /*로그인 페이지로 이동시키는 부분*/
                    }
                })
                .permitAll() /*인가 정책에서 어떠한 페이지도 인증을 받게 설정했기 때문에 로그인 페이지 또한 인증을 받아야 들어갈 수 있지만 이걸 사용하면 login 페이지는 예외 시킬 수 있다*/
                ;

                /*로그아웃 처리하는 부분===================================================================================*/
              http
                      .logout()
                      .logoutUrl("/logout") /*로그아웃 url 주소*/
                      .logoutSuccessUrl("/login") /*로그아웃 성공하면 이동할 url*/
                      .addLogoutHandler(new LogoutHandler() {
                          @Override
                          public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                              HttpSession session = request.getSession();
                              session.invalidate(); /*session을 받아와서 무효화 시키는 부분  로그아웃이 되는 부분이라고 생각하면 쉬울듯*/
                          }
                      }) /*로그아웃할때 사용할 핸들러*/
                     .logoutSuccessHandler(new LogoutSuccessHandler() {
                         @Override
                         public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                             response.sendRedirect("/login"); /*로그아웃을 성공하면 지정해놓은 login 경로로 이동해서 로그인 페이지가 나온다*/
                         }
                     }) /*로그아웃 성공했을 때 사용할 핸들러 위에 logoutSuccessUrl 비슷하지만 handler를 사용하면 더 많은 기능을 사용할 수 있다*/
                    .deleteCookies("remember-me") /*로그아웃할때 삭제하고 싶은 쿠키를 입력하면 쿠키가 삭제된다.*/

              //.and()
              //.rememberMe()  /*id나 pw를 저장하고 있는 쿠키를 생성함*/
              //.rememberMeParameter("remember") /*name 값을 바꾸는 부분 기본값은 remember-me*/
              //.tokenValiditySeconds(3600)  /*토큰이 유지되는 시간 기본 값은 14일이다.*/
              //.userDetailsService(userDetailsService) /*유저정보를 받아오는 서비스를 사용하는 부분*/
              ;

              /*동시 세션제어 하는 부분=================================================================================*/
              http
                      .sessionManagement()
                      .maximumSessions(1) /*최대 허용 가능 세션의 수 , 무제한으로 로그인 허용을 원할시에는 -1을 넣으면 된다.*/
                      .maxSessionsPreventsLogin(false) /*동시 로그인 차단 true면 기존의 접속은 살리고 현재 접속을 못하게 하고, false면 기존의 세션이 만료가 된다.*/
                      .expiredUrl("/expired") /*세션이 만료된 경우 이동 할 페이지*/
              ;
              
        return  http.build();
    }
}
