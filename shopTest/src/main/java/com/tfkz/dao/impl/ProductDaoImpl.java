package com.tfkz.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tfkz.dao.ProductDao;
import com.tfkz.domin.pojo.Category;
import com.tfkz.domin.pojo.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    /**
     *获取产品分类
     */
    @Override
    public List<Category> selectTopCategory(String sid) {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("MyConfig"));
        String sql = "  select id, parent_id, name, status, sort_order, create_time, update_time from neuedu_category where parent_id=?";
        List<Category> c = null;
        try {
            c = qr.query(sql,new BeanListHandler<>(Category.class),sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(c!=null){
            return c;
        }
        return null;
    }

    @Override
    public List<Product> selectBys_NewAndIs_HotAndIs_Banner(Integer is_new, Integer is_hot, Integer is_banner) {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("myConfig"));
        String sql = "     select id, category_id, name, subtitle, main_image, price, stock, status, is_new,is_hot,is_banner,create_time ," +
                "update_time, sub_images, detail from neuedu_product where is_new=? and is_hot=? and is_banner=?";
        List<Product> c = null;
        try {
            c = qr.query(sql,new BeanListHandler<>(Product.class),is_new,is_hot,is_banner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(c!=null){
            return c;
        }
        return null;
}

    @Override
    public Product selectByIdAndIs_New(Integer productId, Integer is_new) {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("myConfig"));
        String sql = "  select id, category_id, name, subtitle, main_image, price, stock, status, is_new,is_hot,is_banner,create_time," +
                " update_time, sub_images, detail from neuedu_product where id=? and is_new=?";
        Product c = null;
        try {
            c = qr.query(sql,new BeanHandler<>(Product.class),productId,is_new);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(c!=null){
            return c;
        }
        return null;
    }

    @Override
    public Product selectByIdAndIs_Hot(Integer productId, Integer is_hot) {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("myConfig"));
        String sql = "  select id, category_id, name, subtitle, main_image, price, stock, status, is_new,is_hot,is_banner,create_time," +
                " update_time, sub_images, detail from neuedu_product where id=? and is_hot=?";
        Product c = null;
        try {
            c = qr.query(sql,new BeanHandler<>(Product.class),productId,is_hot);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(c!=null){
            return c;
        }
        return null;
    }

    @Override
    public Product selectByIdAndIs_Banner(Integer productId, Integer is_banner) {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("myConfig"));
        String sql = "  select id, category_id, name, subtitle, main_image, price, stock, status, is_new,is_hot,is_banner,create_time," +
                " update_time, sub_images, detail from neuedu_product where id=? and is_banner=?";
        Product c = null;
        try {
            c = qr.query(sql,new BeanHandler<>(Product.class),productId,is_banner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(c!=null){
            return c;
        }
        return null;
    }

    @Override
    public Product selectByPrimaryKey(Integer productId) {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource("myConfig"));
        String sql = "     select id, category_id, name, subtitle, main_image, price, stock, status, is_new,is_hot,is_banner,create_time," +
                " update_time, sub_images, detail  where id =?";
        Product c = null;
        try {
            c = qr.query(sql,new BeanHandler<>(Product.class),productId,productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(c!=null){
            return c;
        }
        return null;
    }

    @Override
    public List<Product> getDetail(Integer productId, Integer is_new, Integer is_hot, Integer is_banner) {
        return null;
    }
}
