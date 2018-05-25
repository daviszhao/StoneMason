package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalancePayReceiveCustomQueryRequestBody extends CommonRequest<BalancePayReceiveCustomQueryRequestBody> {
    private String merOrderNo;

    public BalancePayReceiveCustomQueryRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
