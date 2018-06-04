package io.github.daviszhao.stonemason.busEvent.constants;

public enum EventCategory {

    NOTIFY("通知事件"),

    ASK("请求事件"),

    REVOKE("撤销事件"),

    ASKRESP("响应事件");

    private final String desc;

    public String getDesc() {
        return desc;
    }

    EventCategory(String desc) {
        this.desc = desc;
    }

}
