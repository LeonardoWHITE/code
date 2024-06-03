package com.example.controller;

import com.example.bean.Broker;
import com.example.bean.Needs;
import com.example.bean.User;
import com.example.feign.BrokerFeign;
import com.example.feign.UserFeign;
import com.example.service.NeedsService;
import com.example.view.InsuranceView;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("Needs")
public class NeedsController {

    @Autowired
    NeedsService needsService;

    @Autowired
    private BrokerFeign brokerFeign;

    //用户查看需求
    @PostMapping("UserGetNeed")
    public InsuranceView UserGetNeed(String u_username){
        if (String.valueOf(u_username).equals("")){
            return InsuranceView.failure("无法找到指定用户");
        }
        List<Needs> needsList = needsService.UserGetNeed(u_username);
        return InsuranceView.suceess("查询成功").put("needsList",needsList);
    }

    //用户添加需求
    @PostMapping("AddNeeds")
    public InsuranceView AddNeeds(String u_username, String b_name,String needs) throws Exception {
        if (u_username == null || "".equals(u_username)){
            throw new Exception("用户名为空");
        }
        if (b_name == null || "".equals(b_name)){
            throw new Exception("保险经纪人名称为空");
        }
        Boolean need = needsService.AddNeeds(u_username, b_name, needs);
        return InsuranceView.suceess("插入成功").put("需求提交成功",need);
    }

    @PostMapping("Addfeedback")
    public InsuranceView Addfeedback(String u_username, String b_name,String feedback) throws Exception {
        if (u_username == null || "".equals(u_username)){
            throw new Exception("用户名为空");
        }
        if (b_name == null || "".equals(b_name)){
            throw new Exception("保险经纪人名称为空");
        }
        Boolean need = needsService.Addfeedback(u_username, b_name, feedback);
        return InsuranceView.suceess("插入成功").put("需求提交成功",need);
    }

    @PostMapping("/NumberOfNeeds")
    public InsuranceView NumberOfUsers(String u_username){
        int number = needsService.NumberOfNeeds(u_username);
        if (number == 0){
            return InsuranceView.failure("用户查询失败或暂需求");
        }
        return InsuranceView.suceess("查询成功").put("number",number);
    }

    //删除需求
    @PostMapping("delectNeed")
    public InsuranceView delectNeed(Integer n_id){
        if (n_id == null){
            return InsuranceView.failure("删除失败");
        }
        boolean Need = needsService.delectNeed(n_id);
        return InsuranceView.suceess("删除成功").put("n_id",n_id);
    }

    @PostMapping("selectNeedByb_id")
    public InsuranceView selectNeedByb_id(Integer b_id){
        if (b_id == null){
            return InsuranceView.failure("查询失败");
        }
        List<Needs> NeedsList = needsService.selectNeedByb_id(b_id);
        return InsuranceView.suceess("查询成功").put("NeedsList",NeedsList);
    }

    @PostMapping("selectNeedByu_id")
    public InsuranceView selectNeedByu_id(Integer u_id,String b_name){
        if (u_id == null){
            return InsuranceView.failure("查询失败");
        }
        List<Needs> NeedsList = needsService.selectNeedByu_id(u_id,b_name);
        return InsuranceView.suceess("查询成功").put("NeedsList",NeedsList);
    }

    @PostMapping("feedbackNeed")
    public InsuranceView feedbackNeed(Integer n_id, String feedback){
        boolean feedbackNeed = needsService.feedbackNeed(n_id,feedback);
        if (feedbackNeed){
            return InsuranceView.suceess("反馈成功").put("feedbackNeed",feedbackNeed);
        }
        return InsuranceView.failure("反馈失败");
    }

}
