package io.github.daviszhao.stonemason.user.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FeignClient("user-service")
@RestController
public interface UserService {
    @RequestMapping("/hello")
    String hello(@RequestParam("name") String name);
}
