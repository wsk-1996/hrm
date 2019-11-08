package com.yueqian.dao;

import com.yueqian.entity.Job;
import com.yueqian.query.QueryObject;

import java.util.List;

public interface JobDAO {
    List<Job> listAll();
    int update(Job dept);
    int delete(Integer id);
    int insert(Job dept);
    List<Job> listQP(QueryObject qo);
    int listCount(QueryObject qo);
    Job get(Integer id);
}
