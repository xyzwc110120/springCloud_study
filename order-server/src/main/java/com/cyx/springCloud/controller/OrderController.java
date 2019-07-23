package com.cyx.springCloud.controller;

import com.cyx.springCloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/springCloud/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/placeOrder")
    // 进行熔断、降级处理。一旦调用方法失败并抛出了错误信息后，会自动调用 @HystrixCommand 注解标注好的 fallbackMethod 调用类中指定的方法
    @HystrixCommand(
            fallbackMethod = "placeOrderFallback",
            // threadPoolProperties：线程池相关的配置
            threadPoolProperties = {
                    // coreSize：该属性用来设置执行命令线程池的核心线程数，该值也就是命令执行的最大并发量，默认值 10
                    @HystrixProperty(name = "coreSize", value = "3"),
                    // maxQueueSize：该属性用来设置线程池的最大队列大小，当设置为 -1 时，线程池将使用 SynchronousQueue 实现的队列，
                    // 否则使用 LinkedBlockingQueue 实现的队列，默认值 -1
                    @HystrixProperty(name = "maxQueueSize", value = "1")
            })
    public void placeOrder(Long memberId, Long productId) {
        log.info("order-server：下订单");
        System.out.println("开始调用···");
        orderService.placeOrder(memberId, productId);
    }

    // 注意，要与被降级处理的方法参数列表与返回值一致
    public void placeOrderFallback(Long memberId, Long productId) {
        System.out.println("进行降级处理···");
    }


    @RequestMapping("/getHeaders")
    public void getHeaders(HttpServletRequest request) {
        System.out.println("token：" + request.getHeader("token"));
        System.out.println("Cookie：" + request.getHeader("Cookie"));
    }
}
