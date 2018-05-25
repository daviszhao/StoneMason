package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BalanceDefray2BalanceOrderResponseBody extends CommonResponse<BalanceDefray2BalanceOrderResponseBody> {
    private String merOrderNo;
    private String inMerCustomerId;
    private long orderAmount;
    private String orderStatus, orderMessage, jrTradeNo;

}
