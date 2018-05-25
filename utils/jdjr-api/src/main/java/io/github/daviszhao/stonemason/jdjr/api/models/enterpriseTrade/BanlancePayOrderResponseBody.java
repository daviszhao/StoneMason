package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class BanlancePayOrderResponseBody extends CommonResponse<BanlancePayOrderResponseBody> {
    private String merCustomerId;
    private String merOrderNo;
    private long orderAmount;
    private String orderCode;

}
