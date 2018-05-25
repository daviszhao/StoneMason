package io.github.daviszhao.stonemason.order.service;

import io.github.daviszhao.stonemason.user.api.UserService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrderServiceImpl {
    @Inject
    private UserService userService;

    public void hello() {
        System.out.println(userService.hello("name"));
    }
}
