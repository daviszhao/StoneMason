package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BalancePayReceiveCustomQueryResponseBody extends CommonResponse<BalancePayReceiveCustomQueryResponseBody> {
    private String outMerCustomerId, inMerCustomerId, merOrderNo, orderStatus, orderMessage, jrTradeNo;
    private long orderAmount;

}
