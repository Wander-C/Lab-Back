package com.example.tomatomall.service;

import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.StockpileVO;

import java.util.List;

public interface ProductService {
    // 获取商品列表
    List<ProductVO> getAllProducts();
    // 获取指定商品信息
    ProductVO getProduct(Integer id);
    // 更新商品信息 
    Boolean updateProductInfo(ProductVO productVO);
    // 增加商品
    Boolean create(ProductVO productVO);
    // 删除商品
    Boolean delete(Integer id);
    // 调整指定商品的库存
    Boolean alterProductStock(Integer id, Integer number);
    // 查询指定商品的库存 1
    StockpileVO getProductStock(Integer id);
}
