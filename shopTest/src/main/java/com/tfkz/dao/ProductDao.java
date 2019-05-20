package com.tfkz.dao;

import com.tfkz.domin.pojo.Category;
import com.tfkz.domin.pojo.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getDetail(Integer productId, Integer is_new, Integer is_hot, Integer is_banner);
    List<Category> selectTopCategory(String sid);

    List<Product> selectBys_NewAndIs_HotAndIs_Banner(Integer is_new, Integer is_hot, Integer is_banner);

    Product selectByIdAndIs_New(Integer productId, Integer is_new);

    Product selectByIdAndIs_Hot(Integer productId, Integer is_hot);

    Product selectByIdAndIs_Banner(Integer productId, Integer is_banner);

    Product selectByPrimaryKey(Integer productId);
}
