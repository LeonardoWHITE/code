package com.controller;

import com.bean.Broker;
import com.bean.User;
import com.servise.UserService;
import com.view.InsuranceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.json.JsonHelper.toBean;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/anonymous/welcome")
    public InsuranceView welcome() {
        return InsuranceView.suceess("测试接口");
    }

    @GetMapping("welcome")
    public String test() {
        return ("测试接口");
    }

    //获取用户信息
    @PostMapping("/getUser")
    public User getUser(String u_username){
        System.out.println(u_username);
        User user = userService.getUser(u_username);
        return user;
    }

    //更新用户信息
    @PostMapping ("/updateUser")
    public InsuranceView updateUser(String u_username,int u_age,String u_phone,String u_career,String u_address){
        System.out.println(u_address);
        System.out.println(u_age);
        System.out.println(u_career);
        System.out.println(u_phone);
        System.out.println(u_username);
        Boolean update = userService.updateUser(u_username,u_age,u_phone,u_career,u_address);
        return update ?
                InsuranceView.suceess("更新成功").put("用户信息",update):
                InsuranceView.failure("更新失败");
    }

    //插入用户信息
    @PostMapping("/insertUser")
    public InsuranceView insertUser(String u_username,String u_password ,Integer u_age,String u_phone,String u_career,String u_address){
        boolean insert = userService.insertUser(u_username,u_password,u_age,u_phone,u_career,u_address);
        return insert
                ? InsuranceView.suceess("插入成功")
                : InsuranceView.failure("插入失败");
    }

    //获取全部用户信息
    @GetMapping("/getAll")
    public InsuranceView getall(){
        List<User> user = userService.getAll();
        if (user.isEmpty()){
            return InsuranceView.failure("查询失败");
        }
        return InsuranceView.suceess("查询成功").put("userList",user);
    }

    //获取用户名列表
    @GetMapping("/getUsername")
    public InsuranceView getUsername(){
        List<String> usernameList = userService.getUsername();
        if (usernameList.isEmpty()){
            InsuranceView.failure("用户列表为空");
        }
        return InsuranceView.suceess("1").put("用户名列表",usernameList);
    }

    @PostMapping("/login")
    //登录账号
    public InsuranceView login(String u_username,String u_password){
        List<String> usernameList = userService.getUsername();
        if (!usernameList.contains(u_username)){
            return InsuranceView.failure("登录失败，没有该用户");
        }
        User user = userService.getUser(u_username);
        String password = user.getU_password();
        if (!u_password.equals(password)){
            return InsuranceView.failure("密码错误");
        }
        return InsuranceView.suceess("登录成功").put("u_username",u_username);
    }

    @PostMapping("/logon")
    //用户注册
    public InsuranceView logon(String u_username,String u_password){
        List<String> usernameList = userService.getUsername();
        if (usernameList.contains(u_username)){
            return InsuranceView.failure("注册失败，该用户已存在"); 
        }
        Integer age = 0;
        String phone = "";
        String career = "";
        String address = "";
        boolean user = userService.insertUser(u_username,u_password,age,phone,career,address);
        return user
                ? InsuranceView.suceess("注册成功").put("",u_username)
                : InsuranceView.failure("");
    }

    //选择保险经纪人
    @PostMapping("/PickBroker")
    public InsuranceView PickBroker(String u_username,String manager) throws Exception {
        if (u_username == null || "".equals(u_username)){
            throw new Exception("用户名为空");
        }
        if (manager == null || "".equals(manager)){
            throw new Exception("未选择保险经纪人");
        }
        boolean Pick = userService.PickBroker(u_username, manager);
        return Pick
                ? InsuranceView.suceess("选择成功")
                : InsuranceView.failure("选择失败");
    }

    //保险经纪人查看选择自己的用户
    @PostMapping("getUserByBroker")
    public InsuranceView getUserByBroker(String manager) throws Exception {
        if (manager == null || "".equals(manager)){
            throw new Exception("该保险经纪人不存在或未被选择");
        }
        List<User> userByBroker = userService.getUserByBroker(manager);

        return InsuranceView.suceess("查询成功").put("userByBroker",userByBroker);
    }

    //判断用户是否存在
    @PostMapping("/judgmentUser")
    public boolean judgmentUser(String u_username){
        List<String> nameList = userService.getUsername();
        if (!nameList.contains(u_username)){
            return false;
        }
        System.out.println(u_username);
        return true;
    }

    //查询用户个数
    @GetMapping("/NumberOfUsers")
    public InsuranceView NumberOfUsers(){
        int number = userService.NumberOfUsers();
        if (number == 0){
            return InsuranceView.failure("用户查询失败或暂无用户");
        }
        return InsuranceView.suceess("查询成功").put("number",number);
    }

    @PostMapping("relieveBroker")
    public InsuranceView relieveBroker(String u_username){
        boolean relieveBroker = userService.relieveBroker(u_username);
        if (relieveBroker){
            return InsuranceView.suceess("解除成功").put("relieveBroker",relieveBroker);
        }
        return InsuranceView.failure("解除失败");
    }

    @PostMapping("relieveUser")
    public InsuranceView relieveUser(String b_name){
        boolean relieveUser = userService.relieveUser(b_name);
        if (relieveUser){
            return InsuranceView.suceess("解除成功").put("relieveUser",relieveUser);
        }
        return InsuranceView.failure("解除失败");
    }

//    @PostMapping("SubmitRequest")

//    //更新用户信息
//    @PostMapping("/user/updateUserInfo")
//    public InsuranceView updateUserInfo(String username,
//                                   String email,
//                                   String address){
//        if (username == null || "".equals(username)){
//            return InsuranceView.failure("参数错误");
//        }
//
//        boolean update = userService.updateUserDetails(username, email, address);
//
//        return update
//                ? InsuranceView.suceess("更新成功")
//                : InsuranceView.failure("更新失败");
//
//    }
//
//
//    @PostMapping("/user/updatePassword")
//    public InsuranceView updatePassword(String username, String oldPassword, String newPassword){
//
//        boolean updatePassword = userService.updatePassword(username, oldPassword, newPassword);
//        if (updatePassword){
//
//            // 跟新成功后 退出登录
//            SecurityContext context = SecurityContextHolder.getContext();
//            context.setAuthentication(null);
//
//            return InsuranceView.suceess("更新密码成功");
//        }
//
//        return InsuranceView.failure("更新密码失败");
//
//    }



}

