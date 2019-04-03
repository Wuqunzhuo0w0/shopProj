package com.tfkz.domin.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserIn {
    String username;
    String password;
    String email;
    String phone;
    String question;
    String answer;
    public  UserIn(){
        username = "";
        password = "";
        email = "";
        phone = "";
        question = "";
        answer = "";
    }
    public UserIn(String username,String password){
        this.username = username;
        this.password = password;
        email = "";
        phone = "";
        question = "";
        answer = "";
    }
    public UserIn(String username,String password, String email,String phone,String question,String answer){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
    }

}
