package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class AccountPayAcquirerQueryResponseBody extends CommonResponse<AccountPayAcquirerQueryResponseBody> {
    private String outMerCustomerId;
    private String inMerCustomerId;
    private String merOrderNo;
    private String jrTradeNo;
    private String orderStatus;
    private String orderMessage;
    private String finishedTime;
    private String bankSubmitNo;
    private String bankName;
    private long orderAmount;

}
