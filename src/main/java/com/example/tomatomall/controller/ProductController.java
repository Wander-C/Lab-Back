package com.example.tomatomall.controller;

import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.Response;
import com.example.tomatomall.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;
    
    /**
     * 获取所有商品信息
     */
    @GetMapping
    public Response<List<ProductVO>> getAllProducts() {
        return Response.buildSuccess(productService.getAllProducts());
    }
    
    /**
     * 获取单个商品详情
     */
    @GetMapping("/{id}")
    public Response<ProductVO> getProduct(@PathVariable Integer id) {
        return Response.buildSuccess(productService.getProduct(id));
    }
    
    /**
     * 创建新商品
     */
    @PostMapping
    public Response<String> createProduct(@RequestBody ProductVO productVO) {
        if (productService.create(productVO)) {
            return Response.buildSuccess("商品创建成功");
        }
        return Response.buildFailure("商品创建失败", "400");
    }
    
    /**
     * 更新商品信息
     */
    @PutMapping("/{id}")
    public Response<String> updateProduct(@PathVariable Integer id, @RequestBody ProductVO productVO) {
        productVO.setId(id);
        if (productService.updateProductInfo(productVO)) {
            return Response.buildSuccess("商品更新成功");
        }
        return Response.buildFailure("商品更新失败", "400");
    }
    
    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public Response<String> deleteProduct(@PathVariable Integer id) {
        if (productService.delete(id)) {
            return Response.buildSuccess("商品删除成功");
        }
        return Response.buildFailure("商品删除失败", "400");
    }
    
    /**
     * 获取商品库存
     */
    @GetMapping("/{id}/stock")
    public Response<StockpileVO> getProductStock(@PathVariable Integer id) {
        return Response.buildSuccess(productService.getProductStock(id));
    }
    
    /**
     * 调整商品库存
     */
    @PutMapping("/{id}/stock")
    public Response<String> alterProductStock(@PathVariable Integer id, @RequestParam Integer number) {
        if (productService.alterProductStock(id, number)) {
            return Response.buildSuccess("库存调整成功");
        }
        return Response.buildFailure("库存调整失败", "400");
    }
}
