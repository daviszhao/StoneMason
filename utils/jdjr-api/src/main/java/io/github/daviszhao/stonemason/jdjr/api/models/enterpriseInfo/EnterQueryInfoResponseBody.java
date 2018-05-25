package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class EnterQueryInfoResponseBody extends CommonResponse<EnterQueryInfoResponseBody> {
    private String merCustomerId;//20
    private boolean hasPayPwd;//20
    private String merRealStatus;
}
