package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BankCnapsInfoRequestBody extends CommonRequest<BankCnapsInfoRequestBody> {
    private String cityCode, bankCode;

    public BankCnapsInfoRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
