//package com.servise;
//
//import com.example.bean.Insurance_Information;
//import com.example.mapper.InsuranceMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//@Service
//public class InsuranceService {
//
//    @Autowired
//    InsuranceMapper insuranceMapper;
//
//    //查询所有保险信息
//    public List<Insurance_Information> SeleceInsuranceType() {
//        return insuranceMapper.SeleceInsuranceType();
//    }
//
//    public Insurance_Information SeleceInsuranceTypeById(Integer tid) {
//        return insuranceMapper.SeleceInsuranceTypeById(tid);
//    }
//
//
//    public boolean BrokerBuyInsurance(Integer uid, Integer bid, Integer tid) {
//        Insurance_Information information = insuranceMapper.SeleceInsuranceTypeById(tid);
//
//        LocalDateTime starttime = LocalDateTime.now();
//        LocalDateTime endtime = starttime.plus(6, ChronoUnit.MONTHS);
//
//        return insuranceMapper.BrokerBuyInsurance(starttime,endtime,uid,bid,information) == 1;
//    }
//
////    public static void main(String[] args) {
////
////        LocalDateTime starttime = LocalDateTime.now();
////        System.out.println(starttime.plus(6, ChronoUnit.MONTHS));
////
////
////    }
//
//}
