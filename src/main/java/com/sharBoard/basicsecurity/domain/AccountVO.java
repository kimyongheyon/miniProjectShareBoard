package com.sharBoard.basicsecurity.domain;

import lombok.Data;

@Data
public class AccountVO {

    private String username;

    private String password;

    private String user_email;

    private String user_age;

    private String role;

    private String user_phone;

    private String crt_dt;

    private String updt_dt;

    private String user_profile;

    private String user_address;

    private String user_nickname;
}
