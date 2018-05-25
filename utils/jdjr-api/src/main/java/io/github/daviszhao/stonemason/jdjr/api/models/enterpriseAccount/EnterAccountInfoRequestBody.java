package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseAccount;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterAccountInfoRequestBody extends CommonRequest<EnterAccountInfoRequestBody> {
    private String merCustomerId;

    public EnterAccountInfoRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
