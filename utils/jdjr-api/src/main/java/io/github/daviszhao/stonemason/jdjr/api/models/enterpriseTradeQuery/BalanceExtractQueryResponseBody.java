package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BalanceExtractQueryResponseBody extends CommonResponse<BalanceExtractQueryResponseBody> {
    private String outMerCustomerId, inMerCustomerId, merOrderNo;
    private long orderAmount;

}
