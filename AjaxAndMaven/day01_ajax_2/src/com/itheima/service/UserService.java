package com.itheima.service;

import com.itheima.bean.User;

import java.util.List;

public interface UserService {
    /*
        模糊查询
     */
    List<User> selectLike(String username);
}
