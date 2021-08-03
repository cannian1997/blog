package com.evilve.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器实现鉴权
 *
 * @author <liukuankuan>
 * @since 2021/7/28 22:13
 */
@Component
public class AccessFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器业务逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求参数
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //业务逻辑
        if (StringUtils.isEmpty(token)) {
            log.warn("token is null ...");
            ServerHttpResponse response = exchange.getResponse();
            //相应类型
            response.getHeaders().add("Content-Type","application/json;charset=utf-8");
            //响应编码 HTTP 401 表示用户没有访问权限
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应内容
            String message = "{\"message\":\""+HttpStatus.UNAUTHORIZED.getReasonPhrase()+"\"}";
            DataBuffer buffer = response.bufferFactory().wrap(message.getBytes());
            //请求结束 不在继续向下请求
            return response.writeWith(Mono.just(buffer));
        }
        //使用 token 进行身份验证
        log.info(" token is ok !");
        return chain.filter(exchange);
    }

    /**
     * 过滤器执行顺序.数值越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
