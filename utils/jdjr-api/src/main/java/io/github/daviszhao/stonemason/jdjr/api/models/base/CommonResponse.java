package io.github.daviszhao.stonemason.jdjr.api.models.base;

import java.io.Serializable;

@lombok.Setter
@lombok.Getter
public abstract class CommonResponse<T extends CommonResponse<T>> implements Serializable {
    private CommonResponseHeader responseHeader;
    private T responseBody;

}
