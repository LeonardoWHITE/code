package com.example.json;//package com.example.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tojson {
    void listener() {
        File file = null;
        try {
            file = new File("C:\\Users\\desolate soul\\Desktop\\现场数据\\6日20点-7日20点\\6日20点-7日20点.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            List<String> list = new ArrayList<>();
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                if (!innerIP(s)) { //判断是不是内网ip
                    if (!list.contains(s)) { //去重
                        list.add(s);
                    }
                }
            }
            for (String s1 : list) {  //输出 也可以直接写文件里面
                System.out.println(s1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public static boolean innerIP(String ip) {

        Pattern reg = Pattern.compile("^(127\\.0\\.0\\.1)|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})$");

        Matcher match = reg.matcher(ip);

        return match.find();
    }
}
