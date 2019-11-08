package com.yueqian.service.impl;

import com.yueqian.dao.UserDAO;
import com.yueqian.dao.impl.UserDAOImpl;
import com.yueqian.entity.User;
import com.yueqian.query.QueryObject;
import com.yueqian.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDAO userDAO=new UserDAOImpl();
    @Override
    public User login(String loginname, String password) {
        return userDAO.login(loginname,password);
    }

    @Override
    public User get(Integer id) {
        return userDAO.get(id);
    }

    @Override
    public List<User> listAll() {
        return userDAO.listAll();
    }

    @Override
    public int update(User user) {
        return userDAO.update(user);
    }

    @Override
    public int delete(Integer id) {
        return userDAO.delete(id);
    }

    @Override
    public int insert(User user) {
        return userDAO.insert(user);
    }

    @Override
    public List<User> listPage(Integer page, Integer limit) {
        return userDAO.listPage(page,limit);
    }

    @Override
    public List<User> listQP(QueryObject qo) {
        return userDAO.listQP(qo);
    }

    @Override
    public int listCount(QueryObject qo) {
        return userDAO.listCount(qo);
    }
}
