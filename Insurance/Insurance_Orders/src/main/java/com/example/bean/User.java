package com.example.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

;

@Data
public class User {

    public static final String LOGIN_URL = "/Insurance/user/login";
    public static final String LOGOUT_URL = "/api/v1/user/logout";

    private Integer u_id;

    @NotNull(message = "账号不能为空")
    @Size(min = 8, max = 20, message = "账号不符合格式")
    private String u_username;

    @Size(min = 8, max = 20, message = "密码不符合格式")
    @NotNull(message = "密码不能为空")
    private String u_password;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[1|8|9]))\\d{8}$", message = "手机号不符合格式")
    private String u_phone;
    private String u_career;
    private int u_age;
    private String u_address;
    private String manager;

}

