package com.example.mapper;

import com.example.bean.Broker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrokerMapper {

    List<Broker> getAll();

    List<String> getBrokerName();

    Broker getBroekr(String b_name);

    Boolean updateBroker(String b_name,String b_realname, Integer b_age, String b_gender, String b_phone, String b_profile);

    int NumberOfBroker();
}
