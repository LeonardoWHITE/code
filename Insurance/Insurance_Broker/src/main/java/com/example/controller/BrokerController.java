package com.example.controller;

import com.example.bean.Broker;
import com.example.service.BrokerService;
import com.example.view.InsuranceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/Broker")
public class BrokerController {

    @Autowired
    BrokerService brokerService;

    //获取全部用户信息
    @GetMapping("/getAll")
    public InsuranceView getAll(){
        List<Broker> brokers = brokerService.getAll();
        if (brokers.isEmpty()){
            return InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("brokers",brokers);
    }

    //获取用户名列表
    @GetMapping("/getBrokerName")
    public List getBrokerName(){
        List<String> nameList = brokerService.getBrokerName();
        if (nameList.isEmpty()){
            InsuranceView.failure("查询失败");
        }
        return nameList;
    }

    // 判断保险经纪人是否存在
    @PostMapping("/judgmentBroker")
    public boolean judgmentBroker(String b_name){
        List<String> nameList = brokerService.getBrokerName();
        if (!nameList.contains(b_name)){
            return false;
        }
        System.out.println(b_name);
        return true;
    }

//    @PostMapping("getUser")
    //查询所属于该保险经纪人的用户

    //通过用户名获取用户信息
    @PostMapping("/getBroker")
    public Broker getBroker(String b_name){
        System.out.println(b_name);
        Broker broker = brokerService.getBroker(b_name);
        return broker;
    }

    //更新个人信息
    @PostMapping("/updateBroker")
    public InsuranceView updateBroker(String b_name,
                                       String b_realname,
                                       Integer b_age,
                                       String b_gender,
                                       String b_phone,
                                       String b_profile){
        List<String> nameList = brokerService.getBrokerName();
        if (!nameList.contains(b_name)){
            return InsuranceView.failure("无法检索到该用户，更新失败");
        }
        Boolean update = brokerService.updateBroker(b_name,b_realname,b_age,b_gender,b_phone,b_profile);
        return update ?
                InsuranceView.suceess("更新成功").put("用户名",b_name):
                InsuranceView.failure("更新失败");

    }

    @PostMapping("/login")
    //保险经纪人登录
    public InsuranceView BrokerLogin(String b_name,String b_password){
        List<String> nameList = brokerService.getBrokerName();
        if (!nameList.contains(b_name)){
            return InsuranceView.failure("用户不存在，登录失败");
        }
        Broker broker = brokerService.getBroker(b_name);
        String password = broker.getB_password();
        if (!b_password.equals(password)){
            return InsuranceView.failure("密码错误");
        }
        return InsuranceView.suceess("登录成功").put("b_name",b_name);
    }

    @GetMapping("/NumberOfBroker")
    public InsuranceView NumberOfUsers(){
        int number = brokerService.NumberOfBroker();
        if (number == 0){
            return InsuranceView.failure("用户查询失败或暂无经纪人");
        }
        return InsuranceView.suceess("查询成功").put("number",number);
    }

    @GetMapping("/test")
    public String test(){
        RestTemplate restTemplate = new RestTemplate();
        String test = restTemplate.getForObject("http://localhost:8088/user/anonymous/welcome",String.class);
        return test;
//        String resule = template.getClass()
//        String result = template.getForObject("http://127.0.0.1:8001/helloServer", String.class);
    }




}
