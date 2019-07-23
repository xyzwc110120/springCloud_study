package com.cyx.springCloud.mapper;

import com.cyx.springCloud.entities.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void insertOrder(Order order);
}
