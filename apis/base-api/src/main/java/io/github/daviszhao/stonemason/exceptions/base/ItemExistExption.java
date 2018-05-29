package io.github.daviszhao.stonemason.exceptions.base;

public class ItemExistExption extends RuntimeException {
    public ItemExistExption(String itemType) {
        super(itemType + "已存在");
    }
}
