package com.example.mapper;

import com.example.bean.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int AddOrder(
            Integer i_id,
            String i_name,
            String i_price,
            Integer u_id,
            String u_username,
            Integer b_id,
            String b_name,
            String b_e_username,
            String b_e_phone,
            String B_time,
            String E_time,
            String state);

    int NumberOfOrders(String u_username);

    List<Order> getOders(String u_username);

    List<Order> getOdersByBroker(String b_name);

    Order getOdersByID(Integer o_id);

    boolean deldeteOrder(Integer o_id);

    int NumberOfOrdersB(String b_name);

    boolean confirmOrder(String state ,int o_id);

    boolean buyOrder(int o_id);
}
