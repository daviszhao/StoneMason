package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalanceRechargeOrderRequestBody extends CommonRequest<BalanceRechargeOrderRequestBody> {
    private String merCustomerId;
    private String merOrderNo;
    private long orderAmount;
    private String callBackUrl;
    private String memo;

    public BalanceRechargeOrderRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
