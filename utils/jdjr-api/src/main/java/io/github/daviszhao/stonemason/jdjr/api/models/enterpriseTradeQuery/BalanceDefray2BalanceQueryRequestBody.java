package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalanceDefray2BalanceQueryRequestBody extends CommonRequest<BalanceDefray2BalanceQueryRequestBody> {
    private String merOrderNo;

    public BalanceDefray2BalanceQueryRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
