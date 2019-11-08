package com.yueqian.dao;

import com.yueqian.entity.Department;
import com.yueqian.entity.User;
import com.yueqian.query.QueryObject;

import java.util.List;

public interface DeptDAO {
    List<Department> listAll();
    int update(Department dept);
    int delete(Integer id);
    int insert(Department dept);
    List<Department> listQP(QueryObject qo);
    int listCount(QueryObject qo);
    Department get(Integer id);
}
