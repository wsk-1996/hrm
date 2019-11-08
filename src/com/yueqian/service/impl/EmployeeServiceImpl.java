package com.yueqian.service.impl;

import com.yueqian.dao.EmployeeDAO;
import com.yueqian.dao.impl.EmployeeDAOImpl;
import com.yueqian.entity.Employee;
import com.yueqian.query.QueryObject;
import com.yueqian.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO=new EmployeeDAOImpl();
    @Override
    public Employee get(Integer id) {
        return employeeDAO.get(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeDAO.listAll();
    }

    @Override
    public List<Object> listByDeptId(Integer id) {
        return employeeDAO.listByDeptId(id);
    }

    @Override
    public List<Object> listByJobId(Integer id) {
        return employeeDAO.listByJobId(id);
    }

    @Override
    public int updeptnull(Integer id) {
        return employeeDAO.updeptnull(id);
    }

    @Override
    public int upjobnull(Integer id) {
        return employeeDAO.upjobnull(id);
    }

    @Override
    public int update(Employee emp) {
        return employeeDAO.update(emp);
    }

    @Override
    public int delete(Integer id) {
        return employeeDAO.delete(id);
    }

    @Override
    public int insert(Employee emp) {
        return employeeDAO.insert(emp);
    }

    @Override
    public List<Employee> listQP(QueryObject qo) {
        return employeeDAO.listQP(qo);
    }

    @Override
    public int listCount(QueryObject qo) {
        return employeeDAO.listCount(qo);
    }

}
