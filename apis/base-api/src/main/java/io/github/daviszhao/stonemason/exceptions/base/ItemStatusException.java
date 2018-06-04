package io.github.daviszhao.stonemason.exceptions.base;

public abstract class ItemStatusException extends RuntimeException {
    protected ItemStatusException(String type) {
        super(type + "状态错误");
    }
}
