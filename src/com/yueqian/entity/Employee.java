package com.yueqian.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
@Setter
public class Employee {
    public static final Integer SEX_WOMEN=0;
    public static final Integer SEX_MEN=1;
    private Integer id;
    private Department dept;
    private Job job;
    private String name;
    private String cardId;
    private String address;
    private String postCode;
    private String tel;
    private String phone;
    private String qqNum;
    private String email;
    private Integer sex;
    private String party;//政治面貌
    private Date birthday;
    private String race;//民族
    private String education;
    private String speciality;//特长,专业
    private String hobby;
    private String remark;
    private Date createdate;
    public String getSexs(){
        return sex==0?"女":"男";
    }
    public String getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(this.createdate);
        return format;
    }
}
