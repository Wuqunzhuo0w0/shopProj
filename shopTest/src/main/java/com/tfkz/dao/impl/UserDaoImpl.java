package com.tfkz.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tfkz.dao.UserDao;
import com.tfkz.domin.pojo.UserIn;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    //用户登录
    @Override
    public UserIn selectByUnameAndPsd(String email, String password) {
        //创建DBUtils核心类
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("MyConfig"));
        String sql = "SELECT username,`password`,email,phone,question,answer from neuedu_user where email=? and password=?";
        UserIn u = null;
        try {
           u = qr.query(sql, new BeanHandler<UserIn>(UserIn.class), email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
