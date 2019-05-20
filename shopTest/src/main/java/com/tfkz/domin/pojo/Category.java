package com.tfkz.domin.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;
import java.util.Date;


@Getter
@Setter
@ToString
public class Category {
    private Integer id;
    private Integer parent_id;
    private String name;
    private Integer status;
    private Integer sort_order;
    private Date create_time;
    private Date update_time;
}
