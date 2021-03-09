package com.itheima.controller;
//用户控制层

import com.itheima.bean.User;
import com.itheima.service.UserService;
import com.itheima.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    //    创建服务层对象
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和响应的编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //封装成User对象
        req.getMethod();
        User user = new User(username, password);
        //调用业务层的登录方法
        List<User> list = service.login(user);
        //判断是否查询出结果
        if (list.size() != 0) {
            //将用户名存入会话域当中
            req.getSession().setAttribute("username", username);
            //响应给客户端true
            resp.getWriter().write("true");
        } else {
            //响应给客户端false
            resp.getWriter().write("false");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
