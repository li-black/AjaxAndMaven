package com.itheima.service;
//学生业务层接口

import com.github.pagehelper.Page;
import com.itheima.bean.Student;

public interface StudentService {
    //分页查询方法
    Page selectByPage(Integer currentPage, Integer pageSize);

    //添加数据方法
    void addStudent(Student student);

    //修改数据方法
    void updateStu(Student student);

    //删除数据方法
    void deleteStu(String number);
}
