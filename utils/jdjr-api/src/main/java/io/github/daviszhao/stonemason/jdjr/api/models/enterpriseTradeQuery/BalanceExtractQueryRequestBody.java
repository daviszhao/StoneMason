package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalanceExtractQueryRequestBody extends CommonRequest<BalanceExtractQueryRequestBody> {
    private String merOrderNo;

    public BalanceExtractQueryRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
