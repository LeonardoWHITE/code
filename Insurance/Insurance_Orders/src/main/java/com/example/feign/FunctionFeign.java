package com.example.feign;

import com.example.bean.Function;
import com.example.view.InsuranceView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Insurance-Function",path = "Function")
public interface FunctionFeign {

    @PostMapping("judgmentFunction")
    Boolean judgmentFunction(@RequestParam("i_id")Integer i_id);

    @PostMapping("getInsurance")
    Function getInsurance(@RequestParam("i_id")Integer i_id);

}
