package com.yueqian.service;

import com.yueqian.entity.Employee;
import com.yueqian.query.QueryObject;

import java.util.List;

public interface EmployeeService {
    Employee get(Integer id);
    List<Employee> listAll();
    List<Object> listByDeptId(Integer id);
    List<Object> listByJobId(Integer id);
    int updeptnull(Integer id);
    int upjobnull(Integer id);
    int update(Employee emp);
    int delete(Integer id);
    int insert(Employee emp);
    List<Employee> listQP(QueryObject qo);
    int listCount(QueryObject qo);
   /* int deleteBydeptId(Integer id);
    int deleteByjobId(Integer id);*/
}
