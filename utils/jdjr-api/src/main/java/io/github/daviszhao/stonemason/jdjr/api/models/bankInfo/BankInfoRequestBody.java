package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BankInfoRequestBody extends CommonRequest<BankInfoRequestBody> {

    public BankInfoRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return "/bankinfo";
    }
}
