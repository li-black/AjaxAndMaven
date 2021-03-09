package com.itheima.mapper;
//用户的持久层

import com.itheima.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    //    根据传入的user对象查询
    @Select("SELECT * FROM USER WHERE username=#{username} AND password=#{password}")
    List<User> login(User user);
}
