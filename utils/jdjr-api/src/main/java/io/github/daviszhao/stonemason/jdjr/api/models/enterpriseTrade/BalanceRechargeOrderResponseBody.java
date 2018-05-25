package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

import java.util.Map;

@lombok.Setter
@lombok.Getter
public class BalanceRechargeOrderResponseBody extends CommonResponse<BalanceRechargeOrderResponseBody> {
    private String merCustomerId;
    private String merOrderNo;
    private long orderAmount;
    private String cashierUrl;
    private Map<String, String> cashierFormData;

}
