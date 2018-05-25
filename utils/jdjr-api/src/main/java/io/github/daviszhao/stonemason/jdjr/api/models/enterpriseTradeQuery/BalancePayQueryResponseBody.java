package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BalancePayQueryResponseBody extends CommonResponse<BalancePayQueryResponseBody> {
    private String merCustomerId, merOrderNo, orderStatus, orderMessage, jrTradeNo;
    private long orderAmount;

}
