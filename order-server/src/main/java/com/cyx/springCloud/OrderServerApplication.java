package com.cyx.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 启用 Feign
@EnableFeignClients
// 启用断路器
@EnableCircuitBreaker
// 开启 Hystrix 仪表板
@EnableHystrixDashboard
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

    /*
        RestTemplate 是 Spring 提供的用于访问 Rest 服务的客户端。
        RestTemplate 提供了多种便捷访问远程 Http 服务的方法,能够大大提高客户端的编写效率。
        简单说就是：简化了发起 HTTP 请求以及处理响应的过程，并且支持 REST。
    */
    /*@Bean
    // 开启负载均衡
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/
}
