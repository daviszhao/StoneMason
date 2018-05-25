package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalanceDefray2BalanceOrderRequestBody extends CommonRequest<BalanceDefray2BalanceOrderRequestBody> {
    private String merOrderNo;
    private String inMercustomerId;
    private String orderDesc;
    private long orderAmount;

    public BalanceDefray2BalanceOrderRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
