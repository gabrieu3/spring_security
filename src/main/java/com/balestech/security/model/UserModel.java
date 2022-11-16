package com.balestech.security.model;

import com.balestech.security.entity.Authority;
import lombok.Data;

import java.util.Set;

@Data
public class UserModel {
    private String email;
    private String password;
    private Set<Authority> authorities;
}
