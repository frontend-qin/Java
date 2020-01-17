package com.token.entity;

import lombok.Data;

@Data
public class UserToken extends User {
    private String token;
}
