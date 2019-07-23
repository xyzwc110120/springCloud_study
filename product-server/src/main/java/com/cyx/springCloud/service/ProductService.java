package com.cyx.springCloud.service;

import com.cyx.springCloud.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductList();

    Product getProductById(Long id);
}
