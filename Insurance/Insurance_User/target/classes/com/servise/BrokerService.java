//package com.servise;
//
//import com.example.bean.Broker;
//import com.example.mapper.User_BrokerMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BrokerService {
//
//    @Autowired
//    User_BrokerMapper user_brokerMapper;
//
//    public boolean UserChooseBroker(Integer uid, Integer bid) {
//        return user_brokerMapper.UserChooseBroker(uid,bid) == 1;
//    }
//
//    public Broker UserSelectBroker(Integer uid){
//        return user_brokerMapper.UserSelectBroker(uid);
//    }
//
//    public boolean UserSubmitsInsuranceRequest(Integer uid, Integer bid, String need) {
//        return user_brokerMapper.UserSubmitsInsuranceRequest(uid ,bid ,need) == 1;
//    }
//}
