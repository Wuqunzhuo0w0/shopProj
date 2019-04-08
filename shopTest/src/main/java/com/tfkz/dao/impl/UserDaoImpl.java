package com.tfkz.dao.impl;

import com.google.gson.Gson;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tfkz.dao.UserDao;
import com.tfkz.domin.pojo.UserIn;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    //用户登录
    @Override
    public UserIn selectByUnameAndPsd(String uname, String password) {

        Gson gson = new Gson();
        //创建DBUtils核心类
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("MyConfig"));
        String sql = "SELECT username,`password`,email,phone,question,answer from neuedu_user where username=? and password=?";
        UserIn u = null;
        try {
           u = qr.query(sql, new BeanHandler<UserIn>(UserIn.class), uname, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(u!=null){
            return u;
        }
        return null;
    }


    @Override
    public UserIn selectByUsername(String uname) {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("MyConfig"));
        String sql = "SELECT username from neuedu_user where username=?";
        UserIn u = null;
        try {
            u = qr.query(sql, new BeanHandler<UserIn>(UserIn.class), uname);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(u!=null){
            return u;
        }
        return null;
    }

    @Override
    public UserIn selectByEmail(String email) {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("MyConfig"));
        String sql = "SELECT email from neuedu_user where email=?";
        UserIn u = null;
        try {
            u = qr.query(sql,new BeanHandler<>(UserIn.class),email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(u!=null){
            return u;
        }
        return null;
    }

    @Override
    public int insertByUnameAndPsd(String uname, String psd, String email, String phone, String question, String answer) {
        //创建DBUtils核心类
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("MyConfig"));

        //搜索数据库中是否存在对应用户
        String sql = "INSERT INTO `neuedu_user` (`id`, `username`, `password`, `email`, `phone`, `question`, `answer`, `role`, `create_time`, `update_time`) " +
                "VALUES (null, ?, ?, ?, ?, ?, ?, 1, NOW(),NOW())";
        Integer a = 0;
        try {
            a = qr.update(sql,uname,psd,email,phone,question,answer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
}
