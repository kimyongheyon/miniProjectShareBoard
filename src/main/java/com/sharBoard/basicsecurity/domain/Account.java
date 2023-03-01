package com.sharBoard.basicsecurity.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity /*db와 매팅이 되게 하는 부분*/
@Data
@TableGenerator(
        name = "USER_SEQ_GENERATOR"
        , table = "CUSTOM_SEQUENCE"
        , pkColumnValue = "USER_SEQ"
        , allocationSize = 1
)
/*유저를 생성할때 필요한 class*/
@Table(name = "sb_member")
public class Account {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
            , generator = "USER_SEQ_GENERATOR"
    )
    @Column(name = "user_member_id", unique = true)
    private Long Id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_password")
    private String password;

    private String user_email;

    private String user_age;

    @Column(name = "user_role")
    private String role;

    private String user_phone;

    private LocalDateTime crt_dt;

    private LocalDateTime updt_dt;

    private String user_profile;

    private String user_address;

    private String user_nickname;

    @PrePersist
    public void createdAt() {
        this.crt_dt = LocalDateTime.now();
    }
}
