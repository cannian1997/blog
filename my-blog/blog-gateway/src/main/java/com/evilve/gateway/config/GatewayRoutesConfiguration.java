package com.evilve.gateway.config;

import com.evilve.gateway.filter.CustomGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关路由配置类
 *
 * @author <liukuankuan>
 * @since 2021/7/26 20:24
 */
@Configuration
public class GatewayRoutesConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().route(r ->r
                //断言
                .path("/**")
                //目标uri,路由到微服务的地址
                .uri("lb://blog")
                //注册自定义网关过滤器
                .filters(new CustomGatewayFilter())
                //路由 ID,唯一
                .id("blog-gateway")
        ).build();
    }
}
