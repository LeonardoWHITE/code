package com.example.service;

import com.example.bean.Broker;
import com.example.bean.Needs;
import com.example.bean.User;
import com.example.feign.BrokerFeign;
import com.example.feign.UserFeign;
import com.example.mapper.NeedsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NeedsService {

    @Autowired
    NeedsMapper needsMapper;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private BrokerFeign brokerFeign;

    public List<Needs> UserGetNeed(String u_username) {
        return needsMapper.UserGetNeed(u_username);
    }

    public Boolean AddNeeds(String u_username, String b_name,String needs){
        User user = userFeign.getUser(u_username);
        Integer u_id = user.getU_id();
        Broker broker = brokerFeign.getBroker(b_name);
        Integer b_id = broker.getB_id();
        String b_realname = broker.getB_realname();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        return needsMapper.AddNedds(u_id,b_id,needs,date,u_username,b_name,b_realname) == 1;
    }

    public int NumberOfNeeds(String u_username) {
        return needsMapper.NumberOfNeeds(u_username);
    }

    public boolean delectNeed(Integer n_id) {
        return needsMapper.delectNeed(n_id);
    }

    public List<Needs> selectNeedByb_id(Integer b_id) {
        return needsMapper.selectNeedByb_id(b_id);
    }

    public Boolean Addfeedback(String u_username, String b_name, String feedback) {
        User user = userFeign.getUser(u_username);
        Integer u_id = user.getU_id();
        Broker broker = brokerFeign.getBroker(b_name);
        Integer b_id = broker.getB_id();
        String b_realname = broker.getB_realname();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        return needsMapper.Addfeedback(u_id,b_id,feedback,date,u_username,b_name,b_realname) == 1;
    }

    public boolean feedbackNeed(Integer n_id,String feedback) {
        return needsMapper.feedbackNeed(n_id,feedback);
    }

    public List<Needs> selectNeedByu_id(Integer u_id,String b_name) {
        return needsMapper.selectNeedByu_id(u_id,b_name);
    }
}
