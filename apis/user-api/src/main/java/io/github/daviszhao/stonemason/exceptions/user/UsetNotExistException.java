package io.github.daviszhao.stonemason.exceptions.user;

import io.github.daviszhao.stonemason.exceptions.base.ItemNotExistException;

public class UsetNotExistException extends ItemNotExistException {
    public UsetNotExistException(String userName) {
        super("用户[" + userName + "]");
    }

    public UsetNotExistException(int userID) {
        super("用户[" + userID + "]");
    }
}
