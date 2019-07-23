package com.cyx.springCloud.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private int stock;
}
