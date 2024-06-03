package com.example.mapper;


import com.example.bean.Function;
import com.example.bean.grouping;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FunctionMapper {

    List<Function> getAll();

    List<String> getInsuranceName();

    Function getInsurance(Integer i_id);

    List<Integer> getInsuranceId();

    int NumberOfInsurance();

    List<Function> getweight(int i_weight);

    List<Function> InsuranceType(String i_profile);

    List<grouping> grouping();
}
