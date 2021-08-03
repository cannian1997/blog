package com.evilve.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流规则配置
 *
 * @author <liukuankuan>
 * @since 2021/7/28 22:59
 */
@Configuration
public class KeyResolverConfiguration {

    /**
     * 限流规则:uri限流
     * @return
     */
    //@Bean
    public KeyResolver pathKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }

    /**
     * 限流规则:参数限流
     * @return
     */
    @Bean
    public KeyResolver parameterKayResolver(){
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

}
