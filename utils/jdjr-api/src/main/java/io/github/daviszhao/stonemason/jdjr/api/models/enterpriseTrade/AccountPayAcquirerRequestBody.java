package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class AccountPayAcquirerRequestBody extends CommonRequest<AccountPayAcquirerRequestBody> {
    private String outMerCustomerId, inMercustomerId, merOrderNo, memo, callBackUrl, payBankType, payRoleType, PayBankCode;
    private long orderAmount;

    public AccountPayAcquirerRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
