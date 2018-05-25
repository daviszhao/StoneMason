package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalancePayReceiveCustomOrderRequestBody extends CommonRequest<BalancePayReceiveCustomOrderRequestBody> {
    private String outMerCustomerId, inMerCustomerId, merOrderNo, memo;
    private long orderAmount;

    public BalancePayReceiveCustomOrderRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
