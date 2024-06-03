package com.example.service;

import com.example.bean.Broker;
import com.example.mapper.BrokerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerService {

    @Autowired
    BrokerMapper brokerMapper;

    public List<Broker> getAll() {
        return brokerMapper.getAll();
    }

    public List<String> getBrokerName() {
        return brokerMapper.getBrokerName();
    }

    public Broker getBroker(String b_name) {
        return brokerMapper.getBroekr(b_name);
    }

    public Boolean updateBroker(String b_name,
                                String b_realname,
                                Integer b_age,
                                String b_gender,
                                String b_phone,
                                String b_profile) {
        return brokerMapper.updateBroker(b_name,b_realname,b_age,b_gender,b_phone,b_profile);
    }

    public int NumberOfBroker() {
        return brokerMapper.NumberOfBroker();
    }
}
