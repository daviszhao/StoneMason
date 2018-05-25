package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalancePayQueryRequestBody extends CommonRequest<BalancePayQueryRequestBody> {
    private String merOrderNo;

    public BalancePayQueryRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
