package com.sharBoard.basicsecurity.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity /*db와 매팅이 되게 하는 부분*/
@Data
/*유저를 생성할때 필요한 class*/
public class Account {

    @Id
    @GeneratedValue
    private Long Id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
}
