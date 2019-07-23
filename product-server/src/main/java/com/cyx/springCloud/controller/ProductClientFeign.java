package com.cyx.springCloud.controller;

import com.cyx.springCloud.entities.Product;
import com.cyx.springCloud.feign.ProductApiFeign;
import com.cyx.springCloud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ProductClientFeign implements ProductApiFeign {

    private final ProductService productService;

    public ProductClientFeign(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getProductList() {
        return productService.getProductList();
    }

    @Override
    public Product getProductById(@RequestParam("productId") Long id) {
        // 线程睡眠 2 秒
        /*try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info("product-server：单品查询");
        return productService.getProductById(id);
    }
}
