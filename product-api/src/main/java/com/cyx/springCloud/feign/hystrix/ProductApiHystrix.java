package com.cyx.springCloud.feign.hystrix;

import com.cyx.springCloud.entities.Product;
import com.cyx.springCloud.feign.ProductApiFeign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// 实现 Feign 接口，对接口中的方法做降级处理（该类需要声明为 Spring 的 bean 实例）
// 声明为 Spring 的bean 实例
@Component
public class ProductApiHystrix implements ProductApiFeign {

    @Override
    public List<Product> getProductList() {
        return new ArrayList<>();
    }

    @Override
    public Product getProductById(Long id) {
        System.out.println("getProductById：降级处理···");
        return null;
    }
}
