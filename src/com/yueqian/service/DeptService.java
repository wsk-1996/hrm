package com.yueqian.service;

import com.yueqian.entity.Department;
import com.yueqian.query.QueryObject;

import java.util.List;

public interface DeptService {
    List<Department> listAll();
    int update(Department dept);
    int delete(Integer id);
    int insert(Department dept);
    List<Department> listQP(QueryObject qo);
    int listCount(QueryObject qo);
    Department get(Integer id);
}
