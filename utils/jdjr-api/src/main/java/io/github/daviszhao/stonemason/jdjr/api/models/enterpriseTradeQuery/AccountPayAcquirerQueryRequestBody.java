package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class AccountPayAcquirerQueryRequestBody extends CommonRequest<AccountPayAcquirerQueryRequestBody> {
    private String merOrderNo;

    @Override
    public String getRequestUrl() {
        return null;
    }
}
