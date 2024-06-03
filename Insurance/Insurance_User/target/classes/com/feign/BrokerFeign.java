package com.feign;

import com.bean.Broker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Insurance-Broker",path = "/Broker")
public interface BrokerFeign {

    @PostMapping("/getBroker")
    Broker getBroker(@RequestParam("b_name")String manager);

    @GetMapping("/getBrokerName")
    List getBrokerName();

    @PostMapping("/judgmentBroker")
    boolean judgmentBroker(@RequestParam("b_name")String b_name);

}
