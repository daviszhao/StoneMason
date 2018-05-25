package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterModifyInfoRequestBody extends CommonRequest<EnterModifyInfoRequestBody> {
    private String merCustomerId;//30
    private String jrMerMobile;//11

    public EnterModifyInfoRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
