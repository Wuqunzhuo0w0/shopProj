package com.tfkz.dao;

import com.tfkz.domin.pojo.UserIn;

public interface UserDao {
    UserIn selectByUnameAndPsd(String uname, String password);
}
