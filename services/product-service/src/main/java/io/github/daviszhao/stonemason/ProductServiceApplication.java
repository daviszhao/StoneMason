package io.github.daviszhao.stonemason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
