package com.itheima.service.Impl;
//User的服务层

import com.itheima.bean.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import com.itheima.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> login(User user) {
//        创建集合
        List<User> login = null;
//        创建session
        try (SqlSession session = MyBatisUtils.getSession()) {
//            传入UserMapper的class
            UserMapper mapper = session.getMapper(UserMapper.class);
//            调用动态查询的方法
            login = mapper.login(user);
        }
        return login;
    }
}
