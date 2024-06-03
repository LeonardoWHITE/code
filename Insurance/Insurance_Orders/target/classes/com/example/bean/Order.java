package com.example.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    public Integer o_id;

    private Integer i_id;

    private BigDecimal i_price;

    private String i_name;

    public Integer u_id;

    public String u_username;

    public Integer b_id;

    public String b_name;

    public String b_e_username;

    public String b_e_phone;

    private String B_time;

    private String E_time;

    private String state;
}
