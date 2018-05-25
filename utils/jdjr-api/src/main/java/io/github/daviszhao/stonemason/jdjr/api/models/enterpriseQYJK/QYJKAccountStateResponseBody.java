package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseQYJK;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class QYJKAccountStateResponseBody extends CommonResponse<QYJKAccountStateResponseBody> {
    private String accountState, autoRechargeState;

}
