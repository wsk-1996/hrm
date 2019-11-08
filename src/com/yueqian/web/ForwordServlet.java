package com.yueqian.web;

import com.yueqian.entity.Employee;
import com.yueqian.service.DeptService;
import com.yueqian.service.EmployeeService;
import com.yueqian.service.JobService;
import com.yueqian.service.impl.DeptServiceImpl;
import com.yueqian.service.impl.EmployeeServiceImpl;
import com.yueqian.service.impl.JobServiceImpl;
import com.yueqian.util.Myutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forword")
public class ForwordServlet extends HttpServlet {
    DeptService deptService=new DeptServiceImpl();
    JobService jobService=new JobServiceImpl();
    EmployeeService employeeService=new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("tohtml");
        switch (param){
            case "userlist": req.getRequestDispatcher("/WEB-INF/user/list.jsp").forward(req,resp); break;
            case "useradd":req.getRequestDispatcher("/WEB-INF/user/add.jsp").forward(req,resp); break;
            case "deptlist":req.getRequestDispatcher("/WEB-INF/dept/list.jsp").forward(req,resp); break;
            case "deptadd":req.getRequestDispatcher("/WEB-INF/dept/add.jsp").forward(req,resp); break;
            case "joblist":req.getRequestDispatcher("/WEB-INF/job/list.jsp").forward(req,resp); break;
            case "jobadd":req.getRequestDispatcher("/WEB-INF/job/add.jsp").forward(req,resp); break;
            case "emplist":
                req.setAttribute("depts",deptService.listAll());
                req.setAttribute("jobs",jobService.listAll());
                req.getRequestDispatcher("/WEB-INF/emp/list.jsp").forward(req,resp); break;
            case "empadd":
                String id = req.getParameter("id");
                if(Myutil.haslength(id)){
                    Employee employee = employeeService.get(Integer.valueOf(id));
                    System.out.println(employee.getCardId());
                    req.setAttribute("emp",employee);
                }
                req.setAttribute("depts",deptService.listAll());
                req.setAttribute("jobs",jobService.listAll());
                req.getRequestDispatcher("/WEB-INF/emp/add.jsp").forward(req,resp); break;
            case "doclist":req.getRequestDispatcher("/WEB-INF/doc/list.jsp").forward(req,resp); break;
            case "docadd":req.getRequestDispatcher("/WEB-INF/doc/add.jsp").forward(req,resp); break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
