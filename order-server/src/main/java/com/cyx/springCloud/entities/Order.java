package com.cyx.springCloud.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter@Setter
public class Order {

    private Long id;
    private String orderNo;
    private Long memberId;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Date createTime;
}
