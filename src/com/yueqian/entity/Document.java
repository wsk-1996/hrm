package com.yueqian.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Document {
    private Integer id;
    private String title;
    private String filename;
    private String filetype;
    private String filepath;
    private String remark;
    private Date createdate;
    private User user;
    public String getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(this.createdate);
        return format;
    }
}
