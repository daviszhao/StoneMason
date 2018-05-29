package io.github.daviszhao.stonemason.exceptions.user;

import io.github.daviszhao.stonemason.exceptions.base.ItemStatusException;

public class UserStatusException extends ItemStatusException {
    public UserStatusException() {
        super("用户");
    }
}
