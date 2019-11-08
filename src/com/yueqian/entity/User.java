package com.yueqian.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class User {
    public static final int IS_ADMIN=0;//超级管理员
    public static final int IS_NOT_ADMIN=1;//普通用户
    private Integer id;
    private String loginname;//登陆名
    private String password;
    private Integer status;
    private Date createdate;//创建时间
    private String username;//用户名
    public String getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(this.createdate);
        return format;
    }
    public String getSta(){
        return this.status==0?"超级管理员":"普通用户";
    }
   /* public  Map<String,Object> getUserinf(){
        Map<String,Object> map=new HashMap<>();
        map.put("id",this.id);
        map.put("status",this.status);
        map.put("username",this.username);
        map.put("createdate",this.getTime());
        return map;
    }*/
}
