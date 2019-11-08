package com.yueqian.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yueqian.entity.Employee;
import com.yueqian.entity.User;
import com.yueqian.query.EmployeeQueryObject;
import com.yueqian.query.UserQueryObject;
import com.yueqian.service.DeptService;
import com.yueqian.service.EmployeeService;
import com.yueqian.service.JobService;
import com.yueqian.service.UserService;
import com.yueqian.service.impl.DeptServiceImpl;
import com.yueqian.service.impl.EmployeeServiceImpl;
import com.yueqian.service.impl.JobServiceImpl;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/emp")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService=new EmployeeServiceImpl();
    private DeptService deptService=new DeptServiceImpl();
    private JobService jobService=new JobServiceImpl();
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
        EmployeeQueryObject eqo=new EmployeeQueryObject();
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        String deptId=req.getParameter("deptId");
        String jobId=req.getParameter("jobId");
        Employee employee=new Employee();
        Map<String, String[]> parameterMap = req.getParameterMap();
        try {
            BeanUtils.populate(employee,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        eqo.setName(employee.getName());
        eqo.setCardId(employee.getCardId());
        eqo.setPhone(employee.getPhone());
        eqo.setDeptId(deptId);
        eqo.setJobId(jobId);
        eqo.setSex(employee.getSex());
        if (Myutil.haslength(page)) {
            eqo.setCurrentPage(Integer.valueOf(page));
        }
       if(Myutil.haslength(limit)){
           eqo.setPageSize(Integer.valueOf(limit));
       }
        int count = employeeService.listCount(eqo);
        List<Employee> emps = employeeService.listQP(eqo);
        Map<String,Object> map=new HashMap<>();
        map.put("code","0");
        map.put("count",count);
        map.put("data",emps);
        resp.setContentType("application/json;charset=utf-8");
       /* System.out.println(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect));*/
        resp.getWriter().write(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));//出现$ref: "$.list[2]"的原因是因为循环引用/内存对象重复。关闭循环引用
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int count = employeeService.delete(Integer.valueOf(id));
        if(count>0){
            resp.getWriter().write("删除成功");
        }else {
            resp.getWriter().write("删除失败");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        String birth = req.getParameter("birth");
        String deptId = req.getParameter("deptId");
        String jobId = req.getParameter("jobId");
        Employee employee=new Employee();
        try {
            BeanUtils.populate(employee,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        employee.setBirthday(strToDate(birth));
        employee.setDept(deptService.get(Integer.valueOf(deptId)));
        employee.setJob(jobService.get(Integer.valueOf(jobId)));
        if(employee.getId()!=null&&employee.getId()!=0){
            Employee emp = employeeService.get(employee.getId());
            employee.setCreatedate(emp.getCreatedate());
            int count = employeeService.update(employee);
            if(count>0){
                resp.getWriter().write("修改成功");
            }else {
                resp.getWriter().write("修改失败");
            }
        }else {
            employee.setCreatedate(new Date());
            int insert = employeeService.insert(employee);
            String msg="";
            if(insert>0){
                msg="添加成功";
            }else {
                msg="添加失败";
            }
            resp.getWriter().write(msg);
        }

    }
public Date strToDate(String str){
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    Date parse = null;
    try {
        parse = sdf.parse(str);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return parse;
}
}
