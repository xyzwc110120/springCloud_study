package com.cyx.springCloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆权限验证
 */
// 需要在 Spring 中创建实例 bean，交由 Spring 管理
@Component
public class AuthZuulFilter extends ZuulFilter {


    /**
     * 过滤器类型：PRE：前置过滤器；ROUTE：路由过滤器；POST：后置过滤器；ERROR：错误过滤器。
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 执行顺序，数字越小越优先
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否执行过滤，执行过滤所需要的条件
     */
    @Override
    public boolean shouldFilter() {
        // 获取当前线程的请求上下文对象，注意：这里引入的是 com.netflix.zuul.context 包中的 RequestContext 类
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 查看请求路径，判断是否请求的订单服务，如果是则开启过滤
        System.out.println(request.getRequestURI());
        return request.getRequestURI().startsWith("/order/");
    }

    /**
     * 执行过滤，具体业务内容
     */
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 若请求头或参数中都不存在 token 参数，则判断为没登陆
        if (StringUtils.isBlank(request.getHeader("token")) && StringUtils.isBlank(request.getParameter("token"))) {
            // 不发送响应
            context.setSendZuulResponse(false);
            // 设置响应状态码：401
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
