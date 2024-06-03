package com.bcryp;

/*
 * 加密密码
 * */


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

public final class BcrypHelper {
    private BcrypHelper() {

    }

    //对密码进行加密
    public static String Bcrypt(String password) {
        //判断是否为空，报空指针异常
        Objects.requireNonNull(password, "密码不能为空");
        //返回密码为BCrypt方式加密
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //对密码进行校验
    public static boolean matches(String password, String encrypt) {
        //第二个参数encrypt为密文
        return new BCryptPasswordEncoder().matches(password, encrypt);
    }

    public static void main(String[] args) {
        String Bcrypt = Bcrypt("123456");
        System.out.println("加密后密码为"+Bcrypt);
        boolean matches = matches("123456", "$2a$10$jNMah/yW5h6/aAmTXKuE3e0HR4CPxwzrV3cBk5bY0SnrRdSYxq4oW");
        System.out.println(matches);
    }

}
