package com.bean;

import lombok.Data;


@Data
public class Broker {

    Integer b_id;

    private String b_name;

    private String b_password;

    private String gender;

    private String phone;

    private Integer age;
}
