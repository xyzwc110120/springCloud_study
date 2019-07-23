package com.cyx.springCloud.service.impl;

import com.cyx.springCloud.entities.Product;
import com.cyx.springCloud.mapper.ProductMapper;
import com.cyx.springCloud.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getProductList() {
        return productMapper.getProductList();
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.getProductById(id);
    }
}
