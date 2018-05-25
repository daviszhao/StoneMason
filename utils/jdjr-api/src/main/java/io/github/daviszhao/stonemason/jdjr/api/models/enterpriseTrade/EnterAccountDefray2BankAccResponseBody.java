package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class EnterAccountDefray2BankAccResponseBody extends CommonResponse<EnterAccountDefray2BankAccResponseBody> {
    private String merOrderNo;
    private long orderAmount;

}
