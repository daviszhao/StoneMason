package io.github.daviszhao.stonemason.busEvent.constants;

public enum ProcessStatus {
    NEW("未处理"),

    PROCESSED("已处理"),

    IGNORE("忽略");

    private final String desc;

    ProcessStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
