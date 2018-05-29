package io.github.daviszhao.stonemason.exceptions.base;

public abstract class ItemNotExistException extends RuntimeException {
    public ItemNotExistException(String itemType) {
        super(itemType + "不存在");
    }
}
