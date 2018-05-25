package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BalanceExtractOrderResponseBody extends CommonResponse<BalanceExtractOrderResponseBody> {
    private String merCustomerId, merOrderNo, orderCode;
    private long orderAmount;

}
