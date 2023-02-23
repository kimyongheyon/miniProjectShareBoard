package com.sharBoard.basicsecurity.domain;

import lombok.Data;

@Data
public class AccountVO {

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
}
