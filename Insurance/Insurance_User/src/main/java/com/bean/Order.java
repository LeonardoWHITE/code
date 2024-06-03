package com.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    public Integer oid;

    private LocalDateTime B_time;

    private LocalDateTime E_time;

    private BigDecimal i_price;

    private String i_name;

    public Integer t_deadlink;

    public Integer u_id;

    public String u_name;

    public Integer b_id;

    public String b_name;

    public Integer b_e_id;

    public Integer b_e_username;
}
