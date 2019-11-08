package com.yueqian.web;

import com.alibaba.fastjson.JSON;
import com.yueqian.entity.Document;
import com.yueqian.entity.Job;
import com.yueqian.entity.User;
import com.yueqian.query.UserQueryObject;
import com.yueqian.service.DocumentService;
import com.yueqian.service.UserService;
import com.yueqian.service.impl.DocumentServiceImpl;
import com.yueqian.service.impl.UserServiceImpl;
import com.yueqian.util.Myutil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    DocumentService documentService=new DocumentServiceImpl();
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
        UserQueryObject uqo=new UserQueryObject();
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        String username = req.getParameter("username");
        String status = req.getParameter("status");
        uqo.setUsername(username);
        uqo.setStatus(status);
        if (Myutil.haslength(page)) {
            uqo.setCurrentPage(Integer.valueOf(page));
        }
       if(Myutil.haslength(limit)){
           uqo.setPageSize(Integer.valueOf(limit));
       }
        int user = userService.listCount(uqo);
        List<User> users = userService.listQP(uqo);
        Map<String,Object> map=new HashMap<>();
        map.put("code","0");
        map.put("count",user);
        map.put("data",users);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(map));
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String did = req.getParameter("id");
        Integer id=0;
        if(Myutil.haslength(did)){
            id=Integer.valueOf(did);
        }
        User user = userService.get(id);
        if(user!=null) {
            List<Object> docIds = documentService.listByUserId(id);
            for (Object docId : docIds) {
                Integer doid= (Integer) docId;
                Document document = documentService.get(doid);
                File file=new File(document.getFilepath());
                if(file.exists()){
                    file.delete();
                }
            }
            documentService.deleteByUserId(id);
            int count = userService.delete(Integer.valueOf(id));
            if (count > 0) {
                resp.getWriter().write("删除成功");
            } else {
                resp.getWriter().write("删除失败");
            }
        }else {
            int count = userService.delete(Integer.valueOf(id));
            if (count > 0) {
                resp.getWriter().write("删除成功");
            } else {
                resp.getWriter().write("删除失败");
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(user.getId()!=null){
            User user1 = userService.get(user.getId());
            user1.setStatus(user.getStatus());
            user1.setUsername(user.getUsername());
            int count = userService.update(user1);
            if(count>0){
                resp.getWriter().write("修改成功");
            }else {
                resp.getWriter().write("修改失败");
            }
        }else {
            user.setCreatedate(new Date());
            int insert = userService.insert(user);
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
