package io.github.daviszhao.stonemason.user.service;

import io.github.daviszhao.stonemason.user.api.UserService;

import javax.inject.Named;

@Named
public class UserServiceImpl implements UserService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
