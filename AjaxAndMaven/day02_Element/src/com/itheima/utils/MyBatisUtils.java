package com.itheima.utils;
//MyBatis工具类能够直接返回session

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;

    private MyBatisUtils() {
    }

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatisConfig.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    返回自动提交的Session
    public static SqlSession getSession() {
        return sqlSessionFactory.openSession(true);
    }

    //    返回手动提交的Session
    public static SqlSession getNoAutoSession() {
        return sqlSessionFactory.openSession();
    }
}
