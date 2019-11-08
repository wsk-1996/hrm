package com.yueqian.dao.impl;

import com.yueqian.dao.DeptDAO;
import com.yueqian.dao.EmployeeDAO;
import com.yueqian.dao.JobDAO;
import com.yueqian.entity.Department;
import com.yueqian.entity.Employee;
import com.yueqian.entity.Job;
import com.yueqian.query.QueryObject;
import com.yueqian.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOImpl implements EmployeeDAO {
    QueryRunner qr=new QueryRunner(DBUtils.getDataSource());

    @Override
    public Employee get(Integer id) {
        List<Employee> list=new ArrayList<>();
        String sql="select * from employee_inf  where id = ? ";
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
    public List<Employee> listAll() {
        List<Employee> list=null;
        String sql="select * from employee_inf";
        try {
            list=qr.query(sql,new BeanListHandler<Employee>(Employee.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Object> listByDeptId(Integer id) {
        List<Object> list=null;
        String sql="select id from employee_inf where dept_id = ?";
        try {
             list = qr.query(sql,new ColumnListHandler(1), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Object> listByJobId(Integer id) {
        List<Object> list=null;
        String sql="select id from employee_inf where job_id = ?";
        try {
            list = qr.query(sql, new ColumnListHandler(1), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

   /* public static void main(String[] args) {
        EmployeeDAOImpl employeeDAO=new EmployeeDAOImpl();
        List<Object> objects = employeeDAO.listByDeptId(1);
        for (Object object : objects) {
            Integer i= (Integer) object;
            System.out.println(i);
        }
    }*/
    @Override
    public int update(Employee emp) {
        int count=0;
        String sql="update employee_inf set dept_id = ? , job_id = ?,name=?,card_id=?,address=?,post_code=?,tel=?,phone=?,qq_num=?,email=?,sex=?,party=?,birthday=?,race=?,education=?,speciality=?,hobby=?,remark=? where id = ?";

        Object[] params={emp.getDept().getId(),emp.getJob().getId(),emp.getName(),emp.getCardId(),emp.getAddress(),emp.getPostCode(),emp.getTel(),emp.getPhone(),emp.getQqNum(),emp.getEmail(),emp.getSex(),emp.getParty(),emp.getBirthday(),emp.getRace(),emp.getEducation(),emp.getSpeciality(),emp.getHobby(),emp.getRemark(),emp.getId()};

        try {
            count = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public int updeptnull(Integer id) {
        int count=0;
        String sql="update employee_inf set dept_id = ? where dept_id = ?";
        try {
            count = qr.update(sql, null,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public int upjobnull(Integer id) {
        int count=0;
        String sql="update employee_inf set job_id = ? where job_id = ?";
        try {
            count = qr.update(sql, null,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public int delete(Integer id) {
        int count=0;
        String sql="delete from employee_inf where id = ?";
        try {
            count=qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int insert(Employee emp) {
        int count=0;
        String sql="insert into employee_inf (dept_id, job_id,name,card_id,address,post_code,tel,phone,qq_num,email,sex,party,birthday,race,education,speciality,hobby,remark) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params={emp.getDept().getId(),emp.getJob().getId(),emp.getName(),emp.getCardId(),emp.getAddress(),emp.getPostCode(),emp.getTel(),emp.getPhone(),emp.getQqNum(),emp.getEmail(),emp.getSex(),emp.getParty(),emp.getBirthday(),emp.getRace(),emp.getEducation(),emp.getSpeciality(),emp.getHobby(),emp.getRemark()};
        try {
            count=qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    @Override
    public List<Employee> listQP(QueryObject qo) {
        List<Employee> list=new ArrayList<>();
        String sql="select * from employee_inf "+qo.getQuery();
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
    public int listCount(QueryObject qo) {
        int count=0;
        Connection con = DBUtils.getCon();
        String sql="select count(*) as count from employee_inf "+qo.getQuerynolimit();
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

   /* @Override
    public int deleteBydeptId(Integer id) {
        int count=0;
        String sql="delete from employee_inf where dept_id=?";
        try {
            count=qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteByjobId(Integer id) {
        int count=0;
        String sql="delete from employee_inf where job_id=?";
        try {
            count=qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }*/

    private void tors(ResultSet rs,List<Employee> list){
        Map<Integer, Department> dcache=new HashMap<>();
        Map<Integer, Job> jcache=new HashMap<>();
        try {
            while (rs.next()){
                Employee emp=new Employee();
                emp.setId(rs.getInt("id"));
                Integer deptId=rs.getInt("dept_id");
                Integer jobId=rs.getInt("job_id");
                if(dcache.get(deptId)==null){
                    DeptDAO deptDAO=new DeptDAOImpl();
                    dcache.put(deptId,deptDAO.get(deptId));
                }
                emp.setDept(dcache.get(deptId));
                if(jcache.get(jobId)==null){
                    JobDAO jobDAO=new JobDAOImpl();
                    jcache.put(jobId,jobDAO.get(jobId));
                }
                emp.setJob(jcache.get(jobId));
                emp.setName(rs.getString("name"));
                emp.setCardId(rs.getString("card_id"));
                emp.setAddress(rs.getString("address"));
                emp.setPostCode(rs.getString("post_code"));
                emp.setTel(rs.getString("tel"));
                emp.setPhone(rs.getString("phone"));
                emp.setQqNum(rs.getString("qq_num"));
                emp.setEmail(rs.getString("email"));
                emp.setSex(rs.getInt("sex"));
                emp.setParty(rs.getString("party"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setRace(rs.getString("race"));
                emp.setEducation(rs.getString("education"));
                emp.setSpeciality(rs.getString("speciality"));
                emp.setHobby(rs.getString("hobby"));
                emp.setRemark(rs.getString("remark"));
                emp.setCreatedate(rs.getTimestamp("create_date"));
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
