package com.yueqian.service.impl;

import com.yueqian.dao.DeptDAO;
import com.yueqian.dao.impl.DeptDAOImpl;
import com.yueqian.entity.Department;
import com.yueqian.query.QueryObject;
import com.yueqian.service.DeptService;

import java.util.List;

public class DeptServiceImpl implements DeptService {
    DeptDAO deptDAO=new DeptDAOImpl();
    @Override
    public List<Department> listAll() {
        return deptDAO.listAll();
    }

    @Override
    public int update(Department dept) {
        return deptDAO.update(dept);
    }

    @Override
    public int delete(Integer id) {
        return deptDAO.delete(id);
    }

    @Override
    public int insert(Department dept) {
        return deptDAO.insert(dept);
    }

    @Override
    public List<Department> listQP(QueryObject qo) {
        return deptDAO.listQP(qo);
    }

    @Override
    public int listCount(QueryObject qo) {
        return deptDAO.listCount(qo);
    }

    @Override
    public Department get(Integer id) {
        return deptDAO.get(id);
    }
}
