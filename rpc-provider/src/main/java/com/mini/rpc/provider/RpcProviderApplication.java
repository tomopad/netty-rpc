package com.mini.rpc.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author wdl
 * @date 2021/3/11
 */
@EnableConfigurationProperties
@SpringBootApplication
public class RpcProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcProviderApplication.class,args);
    }
}
