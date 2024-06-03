package com.example.servise;

import com.example.bean.Broker;
import com.example.bean.Function;
import com.example.bean.Order;
import com.example.bean.User;
import com.example.feign.BrokerFeign;
import com.example.feign.FunctionFeign;
import com.example.feign.UserFeign;
import com.example.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserFeign userFeign;

    @Autowired
    BrokerFeign brokerFeign;

    @Autowired
    FunctionFeign functionFeign;


    public int AddOrder(String u_username,Integer i_id, String b_name, String b_e_username, String b_e_phone) throws Exception {
        boolean U = userFeign.judgmentUser(u_username);
        if (!U){
            throw new Exception("没有该用户");
        }
        User user = userFeign.getUser(u_username);
        Integer u_id = user.getU_id();

        boolean B = brokerFeign.judgmentBroker(b_name);
        if (!B){
            throw new Exception("没有该保险经纪人");
        }
        Broker broker = brokerFeign.getBroker(b_name);
        Integer b_id = broker.getB_id();
        boolean I = functionFeign.judgmentFunction(i_id);
        if (!I){
            throw new Exception("没有该保险");
        }
        Function insurance = functionFeign.getInsurance(i_id);
        String i_name = insurance.getI_name();
        String i_price = insurance.getI_price();
        Integer i_time = insurance.getI_time();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date date = new Date();
        String B_time = df.format(date);// 当前系统时间
        Date newDate = stepMonth(date, i_time);
        String E_time = df.format(newDate);
        System.out.println("当前时间前1个月的日期：" + df.format(newDate));
        String state = "未签字确认";
        return orderMapper.AddOrder(i_id,i_name,i_price,u_id,u_username,b_id,b_name,b_e_username,b_e_phone,B_time,E_time,state);
    }

    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);

        return c.getTime();
    }

    public int NumberOfOrders(String u_username) {
        return orderMapper.NumberOfOrders(u_username);
    }

    public List<Order> getOders(String u_username) {
        return orderMapper.getOders(u_username);
    }

    public List<Order> getOdersByBroker(String b_name) {
        return orderMapper.getOdersByBroker(b_name);
    }

    public Order getOdersByID(Integer o_id) {
        return orderMapper.getOdersByID(o_id);
    }

    public boolean deldeteOrder(Integer o_id) {
        return orderMapper.deldeteOrder(o_id);

    }

    public int NumberOfOrdersB(String b_name) {
        return orderMapper.NumberOfOrdersB(b_name);
    }

    public boolean confirmOrder(String confirm,String u_username,int o_id){
        Order orders = getOdersByID(o_id);
        if (confirm.equals(u_username)){
            String state = "已签字确认";
            return orderMapper.confirmOrder(state,o_id);
        }
        return false;
    }

    public boolean buyOrder(int o_id) {
        return orderMapper.buyOrder(o_id);
    }
}
