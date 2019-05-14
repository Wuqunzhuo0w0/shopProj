package com.tfkz.dao;

import com.tfkz.domin.pojo.UserIn;

public interface UserDao {
    UserIn selectByUnameAndPsd(String uname, String password);

    UserIn selectByUsername(String uname);

    UserIn selectByEmail(String email);

    //根据用户名和密码插入一条新数据
    int insertByUnameAndPsd(String uname, String psd, String email, String phone, String question, String answer);

    UserIn selectByUsernameAndQuestionAndAnswer(String username, String question, String answer);

    int updateUserPassowrd(String username,String password);
}
