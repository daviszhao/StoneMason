package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BankCityRequestBody extends CommonRequest<BankCityRequestBody> {
    private String provinceCode;

    public BankCityRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
