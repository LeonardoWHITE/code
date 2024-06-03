package com.example.bean;

import lombok.Data;


@Data
public class Broker {

    Integer b_id;

    //用户名
    private String b_name;

    //真实姓名
    private String b_realname;

    //密码
    private String b_password;

    //年龄
    private Integer b_age;

    //性别
    private String b_gender;

    //电话
    private String b_phone;

    //简介
    private String b_profile;
}
