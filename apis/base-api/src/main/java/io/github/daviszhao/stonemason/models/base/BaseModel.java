package io.github.daviszhao.stonemason.models.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class BaseModel implements Serializable {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @JsonIgnore
    protected Integer version;

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
