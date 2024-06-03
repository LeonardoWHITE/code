package com.example.feign;

import com.example.bean.User;
import com.example.view.InsuranceView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@FeignClient(name = "Insurance-User",path = "/user")
public interface UserFeign {

    @PostMapping("/getUser")
    User getUser(@RequestParam("u_username")String u_username);

    @GetMapping("/anonymous/welcome")
    InsuranceView welcome();

    @GetMapping("/welcome")
    String test();

}
