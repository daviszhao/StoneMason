package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class EnterDefray2BankInfoQueryResponseBody extends CommonResponse<EnterDefray2BankInfoQueryResponseBody> {
    private String merOrderNo, orderStatus, orderMessage, jrTradeNo, bankSubmitNo, bankName;
    private long orderAmount;

}
