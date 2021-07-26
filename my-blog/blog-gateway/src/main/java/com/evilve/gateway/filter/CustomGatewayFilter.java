package com.evilve.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义网关过滤器
 *
 * @author <liukuankuan>
 * @since 2021/7/26 20:20
 */
public class CustomGatewayFilter implements GatewayFilter, Ordered {

    /**
     * 过滤器业务逻辑
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("自定义网关过滤器被执行人");
        return chain.filter(exchange); //继续向下执行
    }

    /**
     * 过滤器执行顺序,数值越小,优先级越高
     * @return
     */
    public int getOrder() {
        return 0;
    }
}
