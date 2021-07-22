package com.evilve.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

/**
 * restTemplate配置类
 *
 * @author <liukuankuan>
 * @since 2021/7/19 20:28
 */
@Configuration
public class RestTemplateConfig {

    @Value("${ext.proxy.isProxy}")
    private boolean isProxy;

    @Value("${ext.proxy.ip}")
    private String ip;

    @Value("${ext.proxy.port}")
    private int port;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        if(isProxy){
            SocketAddress address = new InetSocketAddress(ip, port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
            factory.setProxy(proxy);
        }
        factory.setReadTimeout(3000);//单位为ms
        factory.setConnectTimeout(5000);//单位为ms
        return factory;
    }


}
