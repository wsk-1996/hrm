package com.yueqian.web;

import com.alibaba.fastjson.JSON;
import com.yueqian.entity.Department;
import com.yueqian.entity.Employee;
import com.yueqian.entity.User;
import com.yueqian.query.DeptQueryObject;
import com.yueqian.service.DeptService;
import com.yueqian.service.EmployeeService;
import com.yueqian.service.UserService;
import com.yueqian.service.impl.DeptServiceImpl;
import com.yueqian.service.impl.EmployeeServiceImpl;
import com.yueqian.service.impl.UserServiceImpl;
import com.yueqian.util.Myutil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/dept")
public class DeptServlet extends HttpServlet {
    DeptService deptService=new DeptServiceImpl();
    EmployeeService employeeService=new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        String crud = req.getParameter("crud");
        if("list".equals(crud)){
            list(req,resp);
        }else if("delete".equals(crud)){
            delete(req,resp);
        }

    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeptQueryObject dqo=new DeptQueryObject();
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        String name = req.getParameter("name");
        dqo.setName(name);
        if (Myutil.haslength(page)) {
            dqo.setCurrentPage(Integer.valueOf(page));
        }
        if(Myutil.haslength(limit)){
            dqo.setPageSize(Integer.valueOf(limit));
        }
        int count = deptService.listCount(dqo);
        List<Department> depts = deptService.listQP(dqo);
        Map<String,Object> map=new HashMap<>();
        map.put("code","0");
        map.put("count",count);
        map.put("data",depts);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(map));
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String did= req.getParameter("id");
        Integer id=0;
        if(Myutil.haslength(did)){
             id=Integer.valueOf(did);
        }
        Department department = deptService.get(id);
        if(department!=null){
                employeeService.updeptnull(id);
                int count = deptService.delete(Integer.valueOf(id));
                if(count>0){
                    resp.getWriter().write("删除成功");
                }else {
                    resp.getWriter().write("删除失败");
            }
        }else {
            int count = deptService.delete(Integer.valueOf(id));
            if(count>0){
                resp.getWriter().write("删除成功");
            }else {
                resp.getWriter().write("删除失败");
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        Department dept=new Department();
        try {
            BeanUtils.populate(dept,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(dept.getId()!=null){
            int count = deptService.update(dept);
            if(count>0){
                resp.getWriter().write("修改成功");
            }else {
                resp.getWriter().write("修改失败");
            }
        }else {
            int insert = deptService.insert(dept);
            String msg="";
            if(insert>0){
                msg="添加成功";
            }else {
                msg="添加失败";
            }
            resp.getWriter().write(msg);
        }

    }

}
