package com.example.feign;

import com.example.bean.Broker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Insurance-Broker",path = "/Broker")
public interface BrokerFeign {

    @PostMapping("/getBroker")
    Broker getBroker(@RequestParam("b_name")String b_name);

    @PostMapping("/judgmentBroker")
    boolean judgmentBroker(String b_name);

}
