package com.tfkz.domin.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Getter
@Setter
@ToString
public class Product {
    private Integer id;
    private Integer category_id;
    private String name;
    private String subtitle;
    private String main_image;
    private String sub_images;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
    private Integer is_new;
    private Integer is_hot;
    private Integer is_banner;
    private Date create_time;
    private Date update_time;
}
