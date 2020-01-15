package com.admin.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String account;
    private String password;
    private Integer type;
    private Date create;
}