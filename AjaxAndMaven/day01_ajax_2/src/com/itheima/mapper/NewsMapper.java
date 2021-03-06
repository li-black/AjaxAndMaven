package com.itheima.mapper;
//分页查询动态代理接口

import com.itheima.bean.News;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsMapper {
    /*
        查询全部
     */
    @Select("SELECT * FROM news")
    List<News> selectAll();
}
