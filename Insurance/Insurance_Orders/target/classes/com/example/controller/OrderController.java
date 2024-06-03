package com.example.controller;

import com.example.bean.Order;
import com.example.servise.OrderService;
import com.example.view.InsuranceView;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("AddOrder")
    //插入订单
    public InsuranceView AddOrder(String u_username,
                                  String b_name,
                                  Integer i_id,
                                  String b_e_username,
                                  String b_e_phone) throws Exception {
        if (u_username == null || "".equals(u_username)){
            throw new Exception("用户名为空");
        }
        if (b_name == null || "".equals(b_name)){
            throw new Exception("保险经纪人为空");
        }
        if (b_e_username == null || "".equals(b_e_username)){
            throw new Exception("受益人为空");
        }
        if (b_e_phone == null || "".equals(b_e_phone)){
            throw new Exception("受益人号码为空");
        }
        int order = orderService.AddOrder(u_username,i_id,b_name,b_e_username,b_e_phone);
        return order == 1
                ? InsuranceView.suceess("购买成功")
                : InsuranceView.failure("购买失败");
    }

    @PostMapping("NumberOfOrders")
    public InsuranceView NumberOfUsers(String u_username){
        int number = orderService.NumberOfOrders(u_username);
        if (number == 0){
            return InsuranceView.failure("用户查询失败或暂无保险");
        }
        return InsuranceView.suceess("查询成功").put("number",number);
    }

    @PostMapping("NumberOfOrdersB")
    public InsuranceView NumberOfOrdersB(String b_name){
        int number = orderService.NumberOfOrdersB(b_name);
        if (number == 0){
            return InsuranceView.failure("用户查询失败或暂无保险");
        }
        return InsuranceView.suceess("查询成功").put("number",number);
    }

    @PostMapping("getOdersByUser")
    public InsuranceView getOders(String u_username){
        List<Order> list = orderService.getOders(u_username);
        if (list.isEmpty()){
            return InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("list",list);
    }

    @PostMapping("getOdersByBroker")
    public InsuranceView getOdersByBroker(String b_name){
        List<Order> list = orderService.getOdersByBroker(b_name);
        if (list.isEmpty()){
            return InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("list",list);
    }

    @PostMapping("getOdersByID")
    public InsuranceView getOdersByID(Integer o_id){
        Order order = orderService.getOdersByID(o_id);
        if (false){
            return InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("list",order);
    }

    @PostMapping("deldeteOrder")
    public InsuranceView deldeteOrder(Integer o_id){
        boolean Order = orderService.deldeteOrder(o_id);
        return Order
                ? InsuranceView.suceess("删除成功")
                : InsuranceView.failure("删除失败");
    }

    @PostMapping("confirm")
    public InsuranceView confirmOrder(String confirm,String u_username ,int o_id){
        boolean cc = orderService.confirmOrder(confirm,u_username,o_id);
        if (cc){
            return InsuranceView.suceess("签字成功");
        }
        return InsuranceView.failure("签字失败或未填写正确用户名");
    }

    @PostMapping("buyOrder")
    public InsuranceView buyOrder(int o_id){
        Order order = orderService.getOdersByID(o_id);
        if (order.getState().equals("未签字确认")){
            return InsuranceView.failure("该订单未签字确认，不可购买");
        }else if (order.getState().equals("已购买")){
            return InsuranceView.failure("该订单已购买");
        }
        boolean ss = orderService.buyOrder(o_id);
        return InsuranceView.suceess("购买成功");
    }

}
