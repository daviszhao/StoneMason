package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalanceExtractOrderRequestBody extends CommonRequest<BalanceExtractOrderRequestBody> {
    private String merCustomerId, merOrderNo, memo;
    private long orderAmount;

    public BalanceExtractOrderRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
