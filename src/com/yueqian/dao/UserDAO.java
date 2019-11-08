package com.yueqian.dao;

import com.yueqian.entity.User;
import com.yueqian.query.QueryObject;

import java.util.List;

public interface UserDAO {
    User login(String loginname, String password);
    User get(Integer id);
    List<User> listAll();
    int update(User user);
    int delete(Integer id);
    int insert(User user);
    List<User> listPage(Integer page,Integer limit);
    List<User> listQP(QueryObject qo);
    int listCount(QueryObject qo);
}
