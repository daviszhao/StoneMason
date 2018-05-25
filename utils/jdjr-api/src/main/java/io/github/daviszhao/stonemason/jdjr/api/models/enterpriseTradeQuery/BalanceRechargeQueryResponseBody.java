package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BalanceRechargeQueryResponseBody extends CommonResponse<BalanceRechargeQueryResponseBody> {
    private String merCustomerId, merOrderNo, orderStatus, orderMessage, jrTradeNo, bankSubmitNo, bankName;
    private long orderAmount;

}
