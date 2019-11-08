package com.yueqian.dao.impl;

import com.yueqian.dao.DocumentDAO;
import com.yueqian.dao.UserDAO;
import com.yueqian.entity.Document;
import com.yueqian.entity.Employee;
import com.yueqian.entity.User;
import com.yueqian.query.QueryObject;
import com.yueqian.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentDAOImpl implements DocumentDAO {
    QueryRunner qr=new QueryRunner(DBUtils.getDataSource());

    @Override
    public int insert(Document document) {
        int count=0;
        String sql="insert into document_inf (title,filename,filetype,filepath,remark,create_date,user_id) values (?,?,?,?,?,?,?)";
        Object[] param={document.getTitle(),document.getFilename(),document.getFiletype(),document.getFilepath(),document.getRemark(),document.getCreatedate(),document.getUser().getId()};
        try {
            count=qr.update(sql,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Document> listQp(QueryObject qo) {
        List<Document> list=new ArrayList<>();
        String sql="select * from document_inf "+qo.getQuery();
       /* try {
            list=qr.query(sql,new BeanListHandler<Employee>(Employee.class),qo.getParams().toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        Connection con = DBUtils.getCon();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i <qo.getParams().size() ; i++) {
                ps.setObject(i+1,qo.getParams().get(i));
            }
            rs = ps.executeQuery();
            tors(rs,list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        qo.getSqls().clear();
        qo.getParams().clear();
        return list;
    }

    @Override
    public Document get(Integer id) {
        List<Document> list=new ArrayList<>();
        String sql="select * from document_inf  where id = ? ";
       /* try {
            list=qr.query(sql,new BeanListHandler<Employee>(Employee.class),qo.getParams().toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        Connection con = DBUtils.getCon();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            tors(rs,list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list.get(0);
    }
    @Override
    public int listCount(QueryObject qo) {
        int count=0;
        Connection con = DBUtils.getCon();
        String sql="select count(*) as count from document_inf "+qo.getQuerynolimit();
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
    public int delete(Integer id) {
        int count=0;
        String sql="delete from document_inf where id =?";
        try {
            count=qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Object> listByUserId(Integer id) {
        List<Object> list=null;
        String sql="select id from document_inf where user_id = ?";
        try {
            list = qr.query(sql, new ColumnListHandler(1), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteByUserId(Integer id) {
        int count=0;
        String sql="delete from document_inf where user_id =?";
        try {
            count=qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private void tors(ResultSet rs,List<Document> list){
        Map<Integer,User> ucache=new HashMap<>();
        try {
            while (rs.next()){
                Document document=new Document();
                document.setId(rs.getInt("id"));
                document.setFilename(rs.getString("filename"));
                document.setFiletype(rs.getString("filetype"));
                document.setFilepath(rs.getString("filepath"));
                document.setTitle(rs.getString("title"));
                document.setRemark(rs.getString("remark"));
                document.setCreatedate(rs.getTimestamp("create_date"));
                Integer uId=rs.getInt("user_id");
                if(ucache.get(uId)==null){
                    UserDAO userDAO=new UserDAOImpl();
                    User user = userDAO.get(uId);
                    ucache.put(uId,user);
                }
                document.setUser(ucache.get(uId));
                list.add(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
