package com.jsc.jdbcutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class JDBCUtils {
    private  ThreadLocal<Connection> tl = new ThreadLocal<Connection>(); //Map

    //数据源,使用C3P0连接池
    @Autowired
    private DataSource dataSource ;

    /**
     * 获取连接池的方法
     * @return
     */
    public DataSource getDataSource(){
        return dataSource ;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public  Connection getThreadConnection() {
        try{
            //1.先从ThreadLocal上获取
            Connection conn = tl.get();
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3.从数据源中获取一个连接,并且存入ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public  void release(){
        tl.remove();
    }



}
