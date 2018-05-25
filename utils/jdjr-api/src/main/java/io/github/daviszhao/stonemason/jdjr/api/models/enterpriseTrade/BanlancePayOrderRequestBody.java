package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BanlancePayOrderRequestBody extends CommonRequest<BanlancePayOrderRequestBody> {
    private String merCustomerId;
    private String merOrderNo;
    private long orderAmount;
    private String memo;

    public BanlancePayOrderRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
