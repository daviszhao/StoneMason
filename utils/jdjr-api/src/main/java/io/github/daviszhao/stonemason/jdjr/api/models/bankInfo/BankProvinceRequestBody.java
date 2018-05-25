package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BankProvinceRequestBody extends CommonRequest<BankProvinceRequestBody> {

    public BankProvinceRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
