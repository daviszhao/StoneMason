package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class EnterQueryDefrayAuthResponseBody extends CommonResponse<EnterQueryDefrayAuthResponseBody> {
    private String authStatus, authStatusMsg;//20

}
