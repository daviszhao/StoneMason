package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseAccount;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class EnterAccountInfoResponseBody extends CommonResponse<EnterAccountInfoResponseBody> {
    private Long amount;
    private Long frozenAmount;
}
