package com.itheima.service.Impl;
//学生业务层实现类

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.bean.Student;
import com.itheima.mapper.StudentMapper;
import com.itheima.service.StudentService;
import com.itheima.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class StudentServiceImpl implements StudentService {
    //分页查询功能
    @Override
    public Page selectByPage(Integer currentPage, Integer pageSize) {
        Page page = null;
        //获取SqlSession对象
        try (SqlSession session = MyBatisUtils.getSession()) {
            //获取StudentMapper接口实现类对象
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            //设置分页参数
            page = PageHelper.startPage(currentPage, pageSize);
            //调用实现类对象查询全部方法
            mapper.selectByPage();
        }
        return page;
    }

    //添加数据方法
    @Override
    public void addStudent(Student student) {
        //获取SqlSession对象
        try (SqlSession session = MyBatisUtils.getSession()) {
            //获取StudentMapper接口实现类对象
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            //调用实现类对象添加方法
            mapper.addStudent(student);
        }
    }

    //修改数据方法
    @Override
    public void updateStu(Student student) {
        try (SqlSession session = MyBatisUtils.getSession()) {
            //获取StudentMapper接口实现类对象
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            //调用实现类对象修改方法
            mapper.updateStu(student);
        }
    }

    //删除数据方法
    @Override
    public void deleteStu(String number) {
        try (SqlSession session = MyBatisUtils.getSession()) {
            //获取StudentMapper接口实现类对象
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            //调用实现类对象删除方法
            mapper.deleteStu(number);
        }
    }
}
