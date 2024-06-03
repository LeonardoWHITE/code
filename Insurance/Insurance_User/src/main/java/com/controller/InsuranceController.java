//package com.controller;
//
//import com.example.bean.Insurance_Information;
//import com.example.servise.InsuranceService;
//import com.example.view.InsuranceView;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1")
//public class InsuranceController {
//
//    @Autowired
//    InsuranceService insuranceService;
//
//    //查询所有图书信息
//    @GetMapping("/Insurance/SeleceInsuranceType")
//    public InsuranceView SeleceInsuranceType(){
//
//        List<Insurance_Information> information_list = insuranceService.SeleceInsuranceType();
//
//        if (information_list.isEmpty()){
//            InsuranceView.failure("查询失败");
//        }
//
//        return InsuranceView.suceess("查询成功").put("insurance_information",information_list);
//    }
//
//    //用id查询单个图书信息
//    @GetMapping("/Insurance/SeleceInsuranceTypeById")
//    public InsuranceView SeleceInsuranceTypeById(Integer tid){
//
//        Insurance_Information information_list = insuranceService.SeleceInsuranceTypeById(tid);
//
//
//        return InsuranceView.suceess("查询成功").put("insurance_information",information_list);
//    }
//
//    //经纪人帮助用户购买保险
//    @GetMapping("/Insurance/BrokerBuyInsurance")
//    public InsuranceView BrokerBuyInsurance(Integer uid, Integer bid,Integer tid){
//
//        boolean Buy = insuranceService.BrokerBuyInsurance(uid,bid,tid);
//
//        return Buy
//                ? InsuranceView.failure("购买失败")
//                : InsuranceView.suceess("购买成功");
//    }
//
//
//
//}
