package com.cyx.springCloud.feign;

import com.cyx.springCloud.entities.Product;
import com.cyx.springCloud.feign.hystrix.ProductApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 声明一个 Feign 客户端（name：服务名称，fallback：做降级处理的类）
@FeignClient(name = "PRODUCT-SERVER", fallback = ProductApiHystrix.class)
public interface ProductApiFeign {

    @RequestMapping("/springCloud/product/getProductList")
    List<Product> getProductList();

    @RequestMapping("/springCloud/product/getProductById")
    Product getProductById(@RequestParam("productId") Long id);

}