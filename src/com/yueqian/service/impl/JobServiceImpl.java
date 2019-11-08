package com.yueqian.service.impl;

import com.yueqian.dao.DeptDAO;
import com.yueqian.dao.JobDAO;
import com.yueqian.dao.impl.DeptDAOImpl;
import com.yueqian.dao.impl.JobDAOImpl;
import com.yueqian.entity.Department;
import com.yueqian.entity.Job;
import com.yueqian.query.QueryObject;
import com.yueqian.service.DeptService;
import com.yueqian.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    JobDAO jobDAO=new JobDAOImpl();
    @Override
    public List<Job> listAll() {
        return jobDAO.listAll();
    }

    @Override
    public int update(Job job) {
        return jobDAO.update(job);
    }

    @Override
    public int delete(Integer id) {
        return jobDAO.delete(id);
    }

    @Override
    public int insert(Job job) {
        return jobDAO.insert(job);
    }

    @Override
    public List<Job> listQP(QueryObject qo) {
        return jobDAO.listQP(qo);
    }

    @Override
    public int listCount(QueryObject qo) {
        return jobDAO.listCount(qo);
    }

    @Override
    public Job get(Integer id) {
        return jobDAO.get(id);
    }
}
