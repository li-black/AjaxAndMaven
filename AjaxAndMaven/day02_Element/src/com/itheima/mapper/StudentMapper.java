package com.itheima.mapper;
//学生持久层接口

import com.itheima.bean.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {
    //查询数据方法
    @Select("SELECT * FROM student")
    List<Student> selectByPage();

    //添加数据方法
    @Insert("INSERT INTO student VALUES (#{number},#{name},#{birthday},#{address})")
    void addStudent(Student student);

    //修改数据方法
    @Update("UPDATE student SET number=#{number},NAME=#{name},birthday=#{birthday},address=#{address} WHERE number=#{number}")
    void updateStu(Student student);

    //删除数据方法
    @Delete("DELETE FROM student WHERE number=#{number}")
    void deleteStu(String number);
}
