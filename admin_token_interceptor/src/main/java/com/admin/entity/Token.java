package com.admin.entity;

import lombok.Data;

@Data
public class Token {
    private String token;
    private String account;
    private Long id;
    private Integer type;
    public static String saveToken;

}
