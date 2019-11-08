package com.yueqian.web;

import com.yueqian.entity.User;
import com.yueqian.service.UserService;
import com.yueqian.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("USER_IN_SESSION");
        if(user!=null){
            req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
        }else {
            req.setAttribute("msg","请先登陆");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginname = req.getParameter("loginname");
        String randomCode = req.getParameter("randomCode");
        String password=req.getParameter("password");
        String randomcode = (String) req.getSession().getAttribute("RANDOMCODE_IN_SESSION");
        if(!randomcode.equals(randomCode)){
            req.setAttribute("msg","验证码错误!");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }else {
            User user = userService.login(loginname, password);
            if (user != null) {
                req.getSession().setAttribute("USER_IN_SESSION", user);
                req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", "账号或密码错误!");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
    }
}
