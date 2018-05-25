package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterDefray2BankInfoQueryRequestBody extends CommonRequest<EnterDefray2BankInfoQueryRequestBody> {
    private String merOrderNo;

    public EnterDefray2BankInfoQueryRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
