package com.mapper;


import com.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectByusername(String u_username);

    int updateUser(String u_username,int u_age,String u_phone,String u_career,String u_address);

    int insertUser(String u_username,String u_password,Integer u_age,String u_phone,String u_career,String u_address);

    List<User> getAll();

    List<String> getUsername();

    int PickBroker(String u_username, String manager);

    List<User> getUserByBroker(String manager);

    int NumberOfUsers();

    boolean relieveBroker(String u_username);

    boolean relieveUser(String b_name);

//    int add(User user);
//
//    User selectUserDetailsByUsername(String username);
//
//    int updateUserDetailsByUsername(@Param("username") String username,
//                                    @Param("email") String email,
//                                    @Param("address") String address);
//
//
//    int updatePasswordByUsername(@Param("username") String username,
//                                 @Param("password") String password);
}
