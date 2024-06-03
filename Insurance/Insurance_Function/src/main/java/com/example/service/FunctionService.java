package com.example.service;

import com.example.bean.Function;
import com.example.bean.grouping;
import com.example.mapper.FunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionService {

    @Autowired
    FunctionMapper functionMapper;

    public List<Function> getAll() {
        return functionMapper.getAll();
    }

    public List<String> getInsuranceName() {
        return functionMapper.getInsuranceName();
    }

    public Function getInsurance(Integer i_id) {
        return functionMapper.getInsurance(i_id);
    }

    public List<Integer> getInsuranceId() {
        return functionMapper.getInsuranceId();
    }

    public int NumberOfInsurance() {
        return functionMapper.NumberOfInsurance();
    }

    public int recommend(String career, int age, String healthy) {
        int a = career(career);
        int b = age(age);
        int c = healthy(healthy);
        return a+b+c;
    }

    public int career(String career){
        if (career.equals("室内办公人员") || career.equals("轻微体力劳动者") || career.equals("危险系数低劳动者")){
            return 1;
        }else if (career.equals("中等风险")){
            return 2;
        }else{
            return 3;
        }
    }

    public int age(int age){
        if (rangeInDefined(age,18,35)){
            return 1;
        }else if (rangeInDefined(age,36,50)){
            return 2;
        }else{
            return 3;
        }
    }

    public int healthy(String healthy){
        if (healthy.equals("健康")){
            return 1;
        }else if (healthy.equals("有低风险疾病")){
            return 2;
        }else{
            return 3;
        }
    }

    public static boolean rangeInDefined(int current, int min, int max) {
        return Math.max(min, current) == Math.min(current, max);
    }

    public List<Function> getweight(int recommend) {
        return functionMapper.getweight(recommend);
    }

    public List<Function> InsuranceType(String i_profile) {
        return functionMapper.InsuranceType(i_profile);
    }

    public List<grouping> grouping() {
        return functionMapper.grouping();

    }
}
