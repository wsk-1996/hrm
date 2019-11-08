package com.yueqian.query;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter
@Setter
public class QueryObject {
    protected Integer currentPage=1;
    protected Integer pageSize=5;
    protected List<String> sqls=new ArrayList<>();
    protected List<Object> params=new ArrayList<>();
    protected void addparam(String sql,Object... param){
        sqls.add(sql);
        params.addAll(Arrays.asList(param));
    }
    protected  void toparam(){}
    protected void nolimit(){}
    public  String getQuery(){
        toparam();
        StringBuilder sb=new StringBuilder();
        sb.append("where 1=1 ");
        for (int i = 0; i < sqls.size(); i++) {
            sb.append(sqls.get(i));
        }
        return sb.toString();
    }
    public  String getQuerynolimit(){
        nolimit();
        StringBuilder sb=new StringBuilder();
        sb.append("where 1=1 ");
        for (int i = 0; i < sqls.size(); i++) {
            sb.append(sqls.get(i));
        }
        return sb.toString();
    }
}
