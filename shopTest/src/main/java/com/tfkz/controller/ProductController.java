package com.tfkz.controller;


import com.tfkz.common.ServerResponse;
import com.tfkz.services.ProductService;
import com.tfkz.services.impl.ProductServiceImpl;
import com.tfkz.utils.UrlSetUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductController", value = "/product/*")
public class ProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求方法
        String methods = UrlSetUtils.getMethodByUrl(request);

        //2.调用对应的方法
        switch (methods) {
            case "topcategory":
                topcategory(request, response);
                break;
            case "detail":
                detail(request, response);
                break;
            //没有请求类型时返回
            default:
                UrlSetUtils.ErroUrl(request, response);
                break;
        }
    }


    //注入商品业务层
    ProductService ps = new ProductServiceImpl();

    //注入商品分类业务层

    //获取产品分类
    private void topcategory(HttpServletRequest request, HttpServletResponse response) {
        //创建高可复用类接受数据
        ServerResponse sr = null;

        //获取前台传来的商品参数
        String sid = request.getParameter("sid");

        //传到业务层处理,这里直接调用商品分类业务中，获取所有子分类的方法获取即可

        //数据转换成json数据格式
        //数据返回给浏览器
        UrlSetUtils.BackToJson(sr, response);
    }

    //根据商品ID获取产品详情
    private void detail(HttpServletRequest request, HttpServletResponse response) {
        //创建高可复用类接受数据

        //获取前台传来的商品参数
        String productId = request.getParameter("productId");

        //传到业务层处理,这里直接调用商品分类业务中，获取所有子分类的方法获取即可
        ServerResponse sr = ps.selectByProductId(productId);

        //数据转换成json数据格式
        //数据返回给浏览器
        UrlSetUtils.BackToJson(sr, response);

    }
    //产品搜索和动态排序
}
