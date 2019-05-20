package com.tfkz.domin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@ToString
public class ProductVO {

    //图片服务器
    private String imageHost ;


    private Integer id;
    private Integer categoryId;
    private String name;
    private String subTitle;
    private String mainImage;
    private String subImages;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
    private Integer isNew;
    private Integer isHot;
    private Integer isBanner;
    private String createTime;
    private String updateTime;
}
