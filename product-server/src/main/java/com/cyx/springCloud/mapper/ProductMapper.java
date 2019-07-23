package com.cyx.springCloud.mapper;

import com.cyx.springCloud.entities.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> getProductList();

    Product getProductById(Long id);
}
