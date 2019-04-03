package com.tfkz.services;

import com.tfkz.domin.pojo.UserIn;

public interface UserService {
    //用户登录
    UserIn login(String uname, String password);
}
