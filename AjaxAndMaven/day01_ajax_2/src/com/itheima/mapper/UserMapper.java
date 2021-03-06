package com.itheima.mapper;
//模糊查询动态代理接口

import com.itheima.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /*
        模糊查询
     */
    @Select("SELECT * FROM USER WHERE NAME LIKE concat('%',#{username},'%')")
    List<User> selectLike(String username);
}
