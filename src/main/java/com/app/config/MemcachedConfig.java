package com.app.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MemcachedConfig {

    @Value("${memcached.host}")
    private String memcachedHost;

    @Value("${memcached.port}")
    private int memcachedPort;

    @Value("${memcached.poolSize}")
    private int connectionPoolSize;

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        StringBuilder serverAddress = new StringBuilder(memcachedHost).append(":").append(memcachedPort);
        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(serverAddress.toString()));
        builder.setConnectionPoolSize(connectionPoolSize);
        return builder.build();
    }
}
