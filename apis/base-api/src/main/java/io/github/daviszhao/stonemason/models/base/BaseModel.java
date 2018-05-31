package io.github.daviszhao.stonemason.models.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public abstract class BaseModel implements Serializable {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @JsonIgnore
    private Integer version;

}
