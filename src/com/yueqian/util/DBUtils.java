package com.yueqian.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
private static Properties p=new Properties();
private static Connection con;
private static DataSource dataSource;
static {
    try {
        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
         dataSource= DruidDataSourceFactory.createDataSource(p);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public static DataSource getDataSource(){
    return dataSource;
}
public static Connection getCon(){
    try {
        con= dataSource.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return con;
}
}
