package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalanceRechargeQueryRequestBody extends CommonRequest<BalanceRechargeQueryRequestBody> {
    private String merOrderNo;

    public BalanceRechargeQueryRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
