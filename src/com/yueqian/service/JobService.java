package com.yueqian.service;

import com.yueqian.entity.Department;
import com.yueqian.entity.Job;
import com.yueqian.query.QueryObject;

import java.util.List;

public interface JobService {
    List<Job> listAll();
    int update(Job job);
    int delete(Integer id);
    int insert(Job job);
    List<Job> listQP(QueryObject qo);
    int listCount(QueryObject qo);
    Job get(Integer id);
}
