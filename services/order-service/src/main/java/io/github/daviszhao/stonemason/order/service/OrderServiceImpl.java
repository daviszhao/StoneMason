package io.github.daviszhao.stonemason.order.service;

import io.github.daviszhao.stonemason.api.user.UserService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrderServiceImpl {
    @Inject
    private UserService userService;

}
