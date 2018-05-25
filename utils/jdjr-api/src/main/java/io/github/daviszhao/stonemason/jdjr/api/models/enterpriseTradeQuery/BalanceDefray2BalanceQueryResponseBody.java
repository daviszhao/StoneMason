package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BalanceDefray2BalanceQueryResponseBody extends CommonResponse<BalanceDefray2BalanceQueryResponseBody> {
    private String merOrderNo;
    private String inMerCustomerId;
    private long orderAmount;
    private String orderStatus;
    private String orderMessage;
    private String jrTradeNo;
}
