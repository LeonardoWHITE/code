package com.bean;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private Integer rid;
    private String rname;


    private List<Permission> permission;
}
