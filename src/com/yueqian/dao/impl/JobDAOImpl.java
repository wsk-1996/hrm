package com.yueqian.dao.impl;

import com.yueqian.dao.JobDAO;
import com.yueqian.entity.Department;
import com.yueqian.entity.Job;
import com.yueqian.query.QueryObject;
import com.yueqian.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JobDAOImpl implements JobDAO {
    QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
    @Override
    public List<Job> listAll() {
        List<Job> list=null;
        String sql="select * from job_inf";
        try {
            list=qr.query(sql,new BeanListHandler<Job>(Job.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(Job dept) {
        int count=0;
        String sql="update job_inf set name = ?,remark = ? where id = ?";
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
        String sql="delete from job_inf where id = ?";
        try {
            count=qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int insert(Job dept) {
        int count=0;
        String sql="insert into job_inf (name,remark) values (?,?)";
        Object[] params={dept.getName(),dept.getRemark()};
        try {
            count=qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Job> listQP(QueryObject qo) {
        List<Job> list=null;
        String sql="select * from job_inf "+qo.getQuery();
        try {
            list=qr.query(sql,new BeanListHandler<Job>(Job.class),qo.getParams().toArray());
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
        String sql="select count(*) as count from job_inf "+qo.getQuerynolimit();
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
    public Job get(Integer id) {
        Job job=null;
            String sql="select * from job_inf where id=?";
            try {
                job = qr.query(sql, new BeanHandler<Job>(Job.class), id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return job;
    }
}
