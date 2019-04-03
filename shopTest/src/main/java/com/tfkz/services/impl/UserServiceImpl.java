package com.tfkz.services.impl;

import com.tfkz.dao.UserDao;
import com.tfkz.dao.impl.UserDaoImpl;
import com.tfkz.domin.pojo.UserIn;
import com.tfkz.services.UserService;

public class UserServiceImpl implements UserService {

    UserDao ud = new UserDaoImpl();

    @Override
    public UserIn login(String email, String password) {
        if (email==null||email==""||password==null||password==null){

            return null;
        }

        UserIn u = ud.selectByUnameAndPsd(email,password);
        if(u==null){
            //返回错误状态嘛
            return null;
        }
        return u;
    }
}
