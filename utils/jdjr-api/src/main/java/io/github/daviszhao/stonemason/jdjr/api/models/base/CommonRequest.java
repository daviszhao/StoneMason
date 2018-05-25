package io.github.daviszhao.stonemason.jdjr.api.models.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@lombok.Setter
@lombok.Getter
@JsonSerialize
public abstract class CommonRequest<T extends CommonRequest<T>> implements Serializable {
    @JsonIgnore
    private CommonRequestHeader requestHeader;
    @JsonIgnore
    private T requestBody;

    protected CommonRequest() {
        requestHeader = new CommonRequestHeader();
    }

    @JsonIgnore
    public abstract String getRequestUrl();
}
