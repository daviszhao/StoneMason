package io.github.daviszhao.stonemason.services;

import io.github.daviszhao.stonemason.services.xinnet_api.config.XinnetAPIConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(XinnetAPIConfig.class)
public class XinnetApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(XinnetApiApplication.class, args);
    }
}
