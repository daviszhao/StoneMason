package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseQYJK;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class QYJKYieldRateResponseBody extends CommonResponse<QYJKYieldRateResponseBody> {
    private String yieldRate;

}
