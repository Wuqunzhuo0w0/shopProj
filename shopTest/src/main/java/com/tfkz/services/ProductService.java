package com.tfkz.services;

import com.tfkz.common.ServerResponse;

public interface ProductService {
    //根据商品ID获取产品详情
    ServerResponse selectByProductId(String productId);

    ServerResponse topcategory(String sid);

    ServerResponse getDetail(Integer productId, Integer is_new,Integer is_hot,Integer is_banner);
}
