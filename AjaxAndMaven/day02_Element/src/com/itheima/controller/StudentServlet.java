package com.itheima.controller;
//学生控制层

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itheima.bean.Student;
import com.itheima.service.Impl.StudentServiceImpl;
import com.itheima.service.StudentService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private StudentService service = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和响应的编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取方法名
        String method = req.getParameter("method");
        if ("selectByPage".equals(method)) {
            //分页查询功能
            selectByPage(req, resp);
        } else if ("addStudent".equals(method)) {
            //添加数据功能
            addStudent(req, resp);
        } else if ("updateStu".equals(method)) {
            //修改数据功能
            updateStu(req, resp);
        } else if ("deleteStu".equals(method)) {
            //删除数据功能
            deleteStu(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        //调用业务层的查询方法
        Page page = service.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        //封装PageInfo
        PageInfo pageInfo = new PageInfo(page);
        //将info转成json，响应给客户端
        try {
            String json = new ObjectMapper().writeValueAsString(pageInfo);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        //创建Student对象
        final Map<String, String[]> parameterMap = req.getParameterMap();
        Student student = new Student();
        //注册日期转换器方法
        dateConvert();
        //封装Student对象
        try {
            BeanUtils.populate(student, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用业务层的添加方法
        service.addStudent(student);
        try {
            resp.getWriter().write("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStu(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        //创建Student对象
        final Map<String, String[]> parameterMap = req.getParameterMap();
        Student student = new Student();
        //注册日期转换器方法
        dateConvert();
        //封装Student对象
        try {
            BeanUtils.populate(student, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用业务层的修改方法
        service.updateStu(student);
    }

    private void deleteStu(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        String number = req.getParameter("number");
        //调用业务层的删除方法
        service.deleteStu(number);
    }

    //日期转换
    private void dateConvert() {
        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return simpleDateFormat.parse(value.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
    }
}
