package com.yueqian.dao;

import com.yueqian.entity.Document;
import com.yueqian.query.QueryObject;

import java.util.List;

public interface DocumentDAO {
    int insert(Document document);
    List<Document> listQp(QueryObject qo);
    Document get(Integer id);
    int listCount(QueryObject qo);
    int delete(Integer id);
    List<Object> listByUserId(Integer id);
    int deleteByUserId(Integer id);
}
