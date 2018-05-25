package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BalancePayReceiveCustomOrderResponseBody extends CommonResponse<BalancePayReceiveCustomOrderResponseBody> {
    private String outMerCustomerId, inMerCustomerId, merOrderNo, orderCode;
    private long orderAmount;

}
