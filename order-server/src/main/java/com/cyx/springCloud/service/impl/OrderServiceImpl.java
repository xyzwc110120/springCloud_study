package com.cyx.springCloud.service.impl;

import com.cyx.springCloud.entities.Order;
import com.cyx.springCloud.entities.Product;
import com.cyx.springCloud.feign.ProductApiFeign;
import com.cyx.springCloud.mapper.OrderMapper;
import com.cyx.springCloud.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final ProductApiFeign productApiFeign;

    public OrderServiceImpl(OrderMapper orderMapper, ProductApiFeign productApiFeign) {
        this.orderMapper = orderMapper;
        this.productApiFeign = productApiFeign;
    }

    @Override
    public void placeOrder(Long memberId, Long productId) {
        // 发送网络请求，获取商品详情（使用服务名称（product-server）代替 IP 地址（localhost：8100））
        /*
            public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables)：
                发送一个 HTTP GET 请求，返回的响应体将映射为一个对象：
                    url：REST 请求地址，参数使用占位符；
                    responseType：HTTP 响应转换为被转换城的对象类型；
                    uriVariables：请求参数。

            Product product = restTemplate.getForObject(
                "http://PRODUCT-SERVER/springCloud/product/getProductById?id={?}", Product.class, productId);
        */

        // 使用 Feign 远程调用
        /* 注意：这里进行远程请求并不会帮我们将参数与接口参数绑定，所以要注意参数名要去远程请求参数名一致 */
        Product product = productApiFeign.getProductById(productId);

        if (product != null) {
            Order order = new Order();
            // 生成订单号，消除 “-”
            order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
            order.setMemberId(memberId);
            order.setProductId(product.getId());
            order.setProductName(product.getName());
            order.setProductPrice(product.getPrice());

            System.out.println(order);
//            orderMapper.insertOrder(order);
        }
    }
}