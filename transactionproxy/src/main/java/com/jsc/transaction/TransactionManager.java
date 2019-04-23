package com.jsc.transaction;

import com.jsc.jdbcutils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class TransactionManager {
    //获取JDBCUtils
    @Autowired
    private JDBCUtils jdbcUtils;

    //获取Connection开启事务
    public void beginTransaction(){
        try {
            jdbcUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取Connection提交事务
    public  void commit() {
        try {
            jdbcUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取Connection回滚事务
    public  void rollback() {
        try {
            jdbcUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //关闭连接
    public  void release() {
        try {
            jdbcUtils.getThreadConnection().close();//还回连接池中
            jdbcUtils.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
