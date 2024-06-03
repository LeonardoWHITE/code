package com.example.controller;

import com.example.bean.Function;
import com.example.bean.grouping;
import com.example.service.FunctionService;
import com.example.view.InsuranceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("Function")
public class FunctionController {

    @Autowired
    FunctionService functionService;

    //查询所有保险
    @GetMapping("getAll")
    public InsuranceView getAll(){
        List<Function> functionsList = functionService.getAll();
        if (functionsList.isEmpty()){
            return InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("functionsList",functionsList);
    }

    //查询图书名称列表
    @GetMapping("getInsuranceName")
    public InsuranceView getInsuranceName(){
        List<String> nameList = functionService.getInsuranceName();
        if (nameList.isEmpty()){
            InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("图书名称列表",nameList);
    }

    @GetMapping("getInsuranceId")
    public InsuranceView getInsuranceId(){
        List<Integer> idList = functionService.getInsuranceId();
        if (idList.isEmpty()){
            InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("图书名称列表",idList);
    }

    //查询详细信息
    @PostMapping("getInsurance")
    public Function getInsurance(Integer i_id){
        Function function = functionService.getInsurance(i_id);
        return function;
    }

    @PostMapping("judgmentFunction")
    public Boolean judgmentFunction(Integer i_id){
        List<Integer> idList = functionService.getInsuranceId();
        if (!idList.contains(i_id)){
            return false;
        }
        return true;
    }

    @GetMapping("/NumberOfInsurance")
    public InsuranceView NumberOfUsers(){
        int number = functionService.NumberOfInsurance();
        if (number == 0){
            return InsuranceView.failure("用户查询失败或暂无保险");
        }
        return InsuranceView.suceess("查询成功").put("number",number);
    }

    @PostMapping("recommend")
    public InsuranceView recommend(String career,int age,String healthy){
        if (career == null){
            return InsuranceView.failure("职业为空");
        }
        if (age < 18){
            return InsuranceView.failure("年龄为空");
        }
        if (healthy == null){
            return InsuranceView.failure("健康状态为空");
        }
        int recommend = functionService.recommend(career,age,healthy);
        List<Function> functionList = functionService.getweight(recommend);
        return InsuranceView.suceess("推荐保险成功").put("functionList",functionList);
    }

    @PostMapping("InsuranceType")
    public InsuranceView InsuranceType(String i_profile){
        List<Function> functionList = functionService.InsuranceType(i_profile);
        if (functionList.equals("")){
            return InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("functionList",functionList);
    }

    @GetMapping("grouping")
    public InsuranceView grouping(){
        List<grouping> list = functionService.grouping();
        System.out.println(list);
        if (list.isEmpty()){
            return InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("list",list);
    }
}
