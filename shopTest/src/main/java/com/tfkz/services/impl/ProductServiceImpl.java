package com.tfkz.services.impl;

import com.tfkz.common.Const;
import com.tfkz.common.ServerResponse;
import com.tfkz.dao.ProductDao;
import com.tfkz.dao.impl.ProductDaoImpl;
import com.tfkz.domin.pojo.Category;
import com.tfkz.domin.pojo.Product;
import com.tfkz.domin.vo.ProductVO;
import com.tfkz.services.ProductService;
import com.tfkz.utils.POJOtoVOUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDao pd = new ProductDaoImpl();
    @Override
    public ServerResponse selectByProductId(String productId) {
        return null;
    }

    @Override
    public ServerResponse topcategory(String sid) {
        ServerResponse sr = null;
        List<Category> categories = pd.selectTopCategory(sid);
        sr = ServerResponse.createServerResponseBySuccess(categories);
        return  sr;
    }

    @Override
    public ServerResponse getDetail(Integer productId, Integer is_new, Integer is_hot, Integer is_banner) {
        ServerResponse sr = null;

        //判断参数
        if(productId == null && is_new == null && is_hot == null && is_banner == null){
            sr = ServerResponse.createServerResponseByError(Const.ProductStatusEnum.ERROR_PAMAR.getCode(),Const.ProductStatusEnum.ERROR_PAMAR.getDesc());
            return sr;
        }


        if(productId == null){
            List<Product> productList = new ArrayList<>();
            List<ProductVO> voList = new ArrayList<>();
            //搜索最新商品
//            if(is_new ==1 || is_hot ==1 || is_banner == 1){
//                productList = productMapper.selectBys_NewAndIs_HotAndIs_Banner(is_new,is_hot,is_banner);
//            }
//
//            //参数都为0查询所有数据
//            if(is_new ==0 && is_hot ==0 && is_banner == 0){
//                productList = productMapper.selectAll();
//            }

            productList = pd.selectBys_NewAndIs_HotAndIs_Banner(is_new,is_hot,is_banner);

            if(productList == null){
                sr = ServerResponse.createServerResponseByError(Const.ProductStatusEnum.NO_PRODUCT.getCode(),Const.ProductStatusEnum.NO_PRODUCT.getDesc());
                return sr;
            }else{
                for (Product product : productList) {
                    //参数不为空_商品存在_校验商品状态
                    if(product.getStatus() != Const.ProductStatusEnum.PRODUCT_ONLINE.getCode()){
                        //商品不在售
                        sr = ServerResponse.createServerResponseByError(Const.ProductStatusEnum.NO_PRODUCT.getCode(),Const.ProductStatusEnum.NO_PRODUCT.getDesc());
                        return sr;
                    }else{
                        //商品在售，返回商品数据
                        //日期转换成字符串
                        ProductVO productVO = POJOtoVOUtils.getNew(product);
                        voList.add(productVO);
                    }
                }
                sr = ServerResponse.createServerResponseBySuccess(voList);
                return sr;
            }

        }else{

            Product product = null;
            //搜索最新商品
            if(is_new ==1 ){
                product = pd.selectByIdAndIs_New(productId,is_new);
            }
            //搜索最热商品
            else if(is_hot ==1 ){
                product = pd.selectByIdAndIs_Hot(productId,is_hot);
            }
            //搜索banner商品
            else if(is_banner == 1){
                product = pd.selectByIdAndIs_Banner(productId,is_banner);
            }else{
                //搜索普通商品
                product = pd.selectByPrimaryKey(productId);
            }


            //参数不为空但商品不存在
            if(product == null){
                sr = ServerResponse.createServerResponseByError(Const.ProductStatusEnum.NO_PRODUCT.getCode(),Const.ProductStatusEnum.NO_PRODUCT.getDesc());
                return sr;
            }else{
                //参数不为空_商品存在_校验商品状态
                if(product.getStatus() != Const.ProductStatusEnum.PRODUCT_ONLINE.getCode()){
                    //商品不在售
                    sr = ServerResponse.createServerResponseByError(Const.ProductStatusEnum.NO_PRODUCT.getCode(),Const.ProductStatusEnum.NO_PRODUCT.getDesc());
                    return sr;
                }else{
                    //商品在售，返回商品数据
                    //日期转换成字符串
                    ProductVO productVO = POJOtoVOUtils.getNew(product);

                    sr = ServerResponse.createServerResponseBySuccess(productVO);
                    return sr;
                }
            }
        }
    }
}
