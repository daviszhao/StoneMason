package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterRegisterRequestBody extends CommonRequest<EnterRegisterRequestBody> {
    private String merCustomerId;//30
    private String jrMerMobile;//11
    private String authorize;//10,0：未授权；1：授权

    public EnterRegisterRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
