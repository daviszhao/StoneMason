package io.github.daviszhao.stonemason.exceptions.user;

import io.github.daviszhao.stonemason.exceptions.base.ItemExistExption;

public class UserExistExption extends ItemExistExption {
    public UserExistExption() {
        super("用户");
    }
}
