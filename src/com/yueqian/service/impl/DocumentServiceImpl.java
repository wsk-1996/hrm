package com.yueqian.service.impl;

import com.yueqian.dao.DocumentDAO;
import com.yueqian.dao.impl.DocumentDAOImpl;
import com.yueqian.entity.Document;
import com.yueqian.query.QueryObject;
import com.yueqian.service.DocumentService;

import java.util.List;

public class DocumentServiceImpl implements DocumentService {
    DocumentDAO documentDAO=new DocumentDAOImpl();
    @Override
    public int insert(Document document) {
        return documentDAO.insert(document);
    }

    @Override
    public List<Document> listQp(QueryObject qo) {
        return documentDAO.listQp(qo);
    }

    @Override
    public Document get(Integer id) {
        return documentDAO.get(id);
    }

    @Override
    public int listCount(QueryObject qo) {
        return documentDAO.listCount(qo);
    }

    @Override
    public int delete(Integer id) {
        return documentDAO.delete(id);
    }

    @Override
    public List<Object> listByUserId(Integer id) {
        return documentDAO.listByUserId(id);
    }

    @Override
    public int deleteByUserId(Integer id) {
        return documentDAO.deleteByUserId(id);
    }
}
