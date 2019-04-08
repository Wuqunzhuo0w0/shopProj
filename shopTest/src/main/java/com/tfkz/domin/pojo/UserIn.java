package com.tfkz.domin.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserIn {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String question;
    private String answer;
    private String role;

}
