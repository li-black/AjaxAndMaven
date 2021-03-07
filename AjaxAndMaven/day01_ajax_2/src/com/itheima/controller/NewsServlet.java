package com.itheima.controller;
//瀑布流分页查询控制层

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.itheima.service.NewsService;
import com.itheima.service.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newsServlet")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和响应的编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //1.获取请求参数
        String start = req.getParameter("start");
        String pageSize = req.getParameter("pageSize");
        //2.根据当前页码和每页显示的条数，调用业务层的方法，得到分页Page对象
        NewsService service = new NewsServiceImpl();
        Page page = service.pageQuery(Integer.parseInt(start), Integer.parseInt(pageSize));
        //3.将得到的数据转为json
        String json = new ObjectMapper().writeValueAsString(page);
        //4.将数据响应给客户端
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
