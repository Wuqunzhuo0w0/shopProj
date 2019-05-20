package com.tfkz.utils;

import com.tfkz.domin.pojo.Product;
import com.tfkz.domin.vo.ProductVO;

public class POJOtoVOUtils {
    /*product to productVO*/
    public static ProductVO getNew(Product product){
        ProductVO p = new ProductVO();
        p.setId(product.getId());
        p.setCategoryId(product.getCategory_id());
        p.setName(product.getName());
        p.setSubTitle(product.getSubtitle());
        p.setMainImage(product.getMain_image());
        p.setSubImages(product.getSub_images());
        p.setDetail(product.getDetail());
        p.setPrice(product.getPrice());
        p.setStock(product.getStock());
        p.setStatus(product.getStatus());
        p.setIsNew(product.getIs_new());
        p.setIsHot(product.getIs_hot());
        p.setIsBanner(product.getIs_banner());
        p.setCreateTime(DateUtils.dateToStr(product.getCreate_time()));
        p.setUpdateTime(DateUtils.dateToStr(product.getUpdate_time()));
        //设置图片服务器
        p.setImageHost(PropertiesUtils.readByKey("imageHost"));
        return p;
    }
}
