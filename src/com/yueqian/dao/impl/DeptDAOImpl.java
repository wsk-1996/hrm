package com.yueqian.dao.impl;

import com.yueqian.dao.DeptDAO;
import com.yueqian.entity.Department;
import com.yueqian.query.QueryObject;
import com.yueqian.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDAOImpl implements DeptDAO {
    QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
    @Override
    public List<Department> listAll() {
        List<Department> list=null;
        String sql="select * from dept_inf";
        try {
            list=qr.query(sql,new BeanListHandler<Department>(Department.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(Department dept) {
        int count=0;
        String sql="update dept_inf set name = ?,remark = ? where id = ?";
        try {
            count=qr.update(sql,dept.getName(),dept.getRemark(),dept.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int delete(Integer id) {
        int count=0;
        String sql="delete from dept_inf where id = ?";
        try {
            count=qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int insert(Department dept) {
        int count=0;
        String sql="insert into dept_inf (name,remark) values (?,?)";
        Object[] params={dept.getName(),dept.getRemark()};
        try {
            count=qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Department> listQP(QueryObject qo) {
        List<Department> list=null;
        String sql="select * from dept_inf "+qo.getQuery();
        try {
            list=qr.query(sql,new BeanListHandler<Department>(Department.class),qo.getParams().toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        qo.getSqls().clear();
        qo.getParams().clear();
        return list;
    }

    @Override
    public int listCount(QueryObject qo) {
        int count=0;
        Connection con = DBUtils.getCon();
        String sql="select count(*) as count from dept_inf "+qo.getQuerynolimit();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i <qo.getParams().size(); i++) {
                ps.setObject(i+1,qo.getParams().get(i));
            }
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                count=resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        qo.getSqls().clear();
        qo.getParams().clear();
        return count;
    }

    @Override
    public Department get(Integer id) {
        Department dept=null;
        String sql="select * from dept_inf where id=?";
        try {
            dept = qr.query(sql, new BeanHandler<Department>(Department.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dept;
    }

 /*   public static void main(String[] args) {
        String str="a-b-c-d-e";
        char[] chars = str.toCharArray();
        String str1="";
        for (int i = 0; i <chars.length ; i++) {
            str1=str1+chars[chars.length-i-1];
        }
        System.out.println(str1.replace("c","c".toUpperCase()));
        StringBuilder sb=new StringBuilder(str);
        sb.reverse();
        sb.replace(sb.indexOf("c"),sb.indexOf("c")+1,"c".toUpperCase());
        System.out.println(sb.toString().replace("c","c".toUpperCase()));
    }*/
/* public static void main(String[] args) {
     List<String> list = new ArrayList<String>();
      list.add("测试 1");
      list.add("测试 2");
      list.add("测试 3");
      list.add("测试 4");
      list.add("测试 4");
     list.add("测试 2");
      list.add("测试 5");
      System.out.println("没有去重前的数据为>>>"+list.toString());
      for(int i = 0;i<list.size()-1;i++){
          for(int j = list.size()-1;j>i;j--) {
              if(list.get(j).equals(list.get(i))){
                  list.remove(j);
                  }
              }
          }
      System.out.println("去重后的数据为>>>"+list.toString());
 }*/

}
