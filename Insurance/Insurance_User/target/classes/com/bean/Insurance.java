package com.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Insurance {
    public Integer i_id;
    private String i_name;
    private BigDecimal i_price;
    public Integer i_profile;
    public Data i_time;
}
