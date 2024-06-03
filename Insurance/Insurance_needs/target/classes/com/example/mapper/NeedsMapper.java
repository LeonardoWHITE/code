package com.example.mapper;

import com.example.bean.Broker;
import com.example.bean.Needs;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface NeedsMapper {

    List<Needs> UserGetNeed(String u_username);

    int AddNedds(Integer u_id, Integer b_id, String needs, Date date, String u_username, String b_name,String b_realname);

    int NumberOfNeeds(String u_username);

    boolean delectNeed(Integer n_id);

    List<Needs> selectNeedByb_id(Integer b_id);

    int Addfeedback(Integer u_id, Integer b_id, String feedback, Date date, String u_username, String b_name, String b_realname);

    boolean feedbackNeed(Integer n_id,String feedback);

    List<Needs> selectNeedByu_id(Integer u_id,String b_name);
}
