package com.servise;

import com.bcryp.BcrypHelper;
import com.bean.Broker;
import com.bean.User;

import com.feign.BrokerFeign;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BrokerFeign brokerFeign;

    //注册操作
//    public boolean register(User user) {
//        // 2. 加密密码
//        String password = user.getPassword();
//        String bcrypt = BcrypHelper.Bcrypt(password);
//        user.setPassword(bcrypt);
//
//        return userMapper.add(user) == 1;
//    }
//
//    //获取用户详细信息
//    public User getUserDetails(String username) {
//        return userMapper.selectUserDetailsByUsername(username);
//    }

    //获取用户信息
    public User getUser(String u_username){
        return userMapper.selectByusername(u_username);
    }

    public boolean updateUser(String u_username,int u_age,String u_phone,String u_career, String u_address){
        return userMapper.updateUser(u_username,u_age,u_phone,u_career,u_address) == 1;
    }

    public boolean insertUser(String u_username,String u_password ,Integer u_age,String u_phone,String u_career,String u_address){
        return userMapper.insertUser(u_username,u_password,u_age,u_phone,u_career,u_address) == 1;
    }

    public List<User> getAll() {
        return userMapper.getAll();
    }

    public List<String> getUsername() {
        return userMapper.getUsername();
    }

    public boolean PickBroker(String u_username, String manager) throws Exception {
        boolean b = brokerFeign.judgmentBroker(manager);
        if (!b){
            throw new Exception("该经纪人不存在");
        }
        return userMapper.PickBroker(u_username,manager) == 1;
    }

    public List<User> getUserByBroker(String manager) throws Exception {
        boolean b = brokerFeign.judgmentBroker(manager);
        if (!b){
            throw new Exception("该经纪人不存在");
        }
        return userMapper.getUserByBroker(manager);
    }

    public int NumberOfUsers() {
        return userMapper.NumberOfUsers();
    }

    public boolean relieveBroker(String u_username) {
        return userMapper.relieveBroker(u_username);
    }

    public boolean relieveUser(String b_name) {
        return userMapper.relieveUser(b_name);
    }

    //更新用户信息
//    public boolean updateUserDetails(String username, String email, String address) {
//        return userMapper.updateUserDetailsByUsername(username, email, address) == 1;
//    }
//
//    //更新密码
//    public boolean updatePassword(String username,
//                                  String oldPassword,
//                                  String newPassword) {
//
//        // 1. 查询用户的加密密码是多少
//
//        User user = userMapper.selectByusername(username);
//        if (BcrypHelper.matches(oldPassword, user.getPassword())) {
//
//            String encrypt = BcrypHelper.Bcrypt(newPassword);
//            return userMapper.updatePasswordByUsername(username, encrypt) == 1;
//        }
//
//        return false;
//    }
}