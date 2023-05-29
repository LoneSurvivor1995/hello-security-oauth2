package com.fsk.oauth2server.domain;


import lombok.Data;

@Data
public class MyUser {
    private String userName;
    private String password;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked= true;
    private boolean credentialsNonExpired= true;
    private boolean enabled= true;
}
