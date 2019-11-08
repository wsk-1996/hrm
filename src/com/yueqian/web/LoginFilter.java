package com.yueqian.web;

import com.yueqian.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if(uri.contains("login")||uri.contains("randomCode")||uri.contains("index")||uri.equals("/")||uri.contains("logout")||uri.contains("css")||uri.contains("js")||uri.contains("jpg")){
            filterChain.doFilter(request,response);
        }else {
            User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
            if(user!=null){
                filterChain.doFilter(request,response);
            }else {
                request.setAttribute("msg","请先登陆");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
