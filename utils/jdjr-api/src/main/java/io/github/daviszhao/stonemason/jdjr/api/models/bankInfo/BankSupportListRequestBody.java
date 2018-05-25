package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class BankSupportListRequestBody extends CommonRequest<BankSupportListRequestBody> {
    /**
     * 业务类型，
     * ENTER_ACQUIRER 对公收银台;
     * PERSONAL_ACQUIRER：对私收银台
     */
    private String businessType;

    public BankSupportListRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
