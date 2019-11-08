package com.yueqian.dao.impl;

import com.yueqian.dao.UserDAO;
import com.yueqian.entity.User;
import com.yueqian.query.QueryObject;
import com.yueqian.util.DBUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
    @Override
    public User login(String loginname, String password) {
        User user=null;
        String sql="select * from user_inf where loginname = ? and password = ?";
        try {
            user = qr.query(sql, new BeanHandler<User>(User.class), loginname, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User get(Integer id) {
        User user=null;
        String sql="select * from user_inf where id=?";
        try {
            user = qr.query(sql, new BeanHandler<User>(User.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> listAll() {
        List<User> list=null;
        String sql="select * from user_inf";
        try {
            list=qr.query(sql,new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(User user) {
        int count=0;
        String sql="update user_inf set username = ? , status = ? where id = ?";
        Object[] params={user.getUsername(),user.getStatus(),user.getId()};
        try {
            count = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int delete(Integer id) {
        int count=0;
        String sql="delete from user_inf where id = ?";
        try {
            count=qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int insert(User user) {
        int count=0;
        String sql="insert into user_inf (loginname,password,status,createdate,username) values (?,?,?,?,?)";
        Object[] params={user.getLoginname(),user.getPassword(),user.getStatus(),user.getCreatedate(),user.getUsername()};
        try {
            count=qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<User> listPage(Integer page, Integer limit) {
        List<User> list=null;
        Integer start=(page-1)*limit;
        String sql="select * from user_inf limit ?,?";
        try {
            list=qr.query(sql,new BeanListHandler<User>(User.class),start,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<User> listQP(QueryObject qo) {
        List<User> list=null;
        String sql="select * from user_inf "+qo.getQuery();
        try {
            list=qr.query(sql,new BeanListHandler<User>(User.class),qo.getParams().toArray());
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
        String sql="select count(*) as count from user_inf "+qo.getQuerynolimit();
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
}
