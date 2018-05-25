package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BalanceDefray2BankOrderRequestBody extends CommonRequest<BalanceDefray2BankOrderRequestBody> {
    private String merOrderNo, outMerCustomerId, inMerCustomerId, bankCode, bankName, bankAccountName, bankAccountNo, memo, cardType, bankAccountType, city, province, subsidiaryName, bankCnaps, jrMerMobile;
    private long orderAmount;

    public BalanceDefray2BankOrderRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
