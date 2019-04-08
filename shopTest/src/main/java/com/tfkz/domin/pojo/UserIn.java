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
    String role;

}
