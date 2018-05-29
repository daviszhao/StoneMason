package io.github.daviszhao.stonemason.user.service;

import io.github.daviszhao.stonemason.api.user.UserService;
import io.github.daviszhao.stonemason.models.user.User;

import javax.inject.Named;

@Named
public class UserServiceImpl implements UserService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public User register(String userName, String password) {
        return null;
    }
}
