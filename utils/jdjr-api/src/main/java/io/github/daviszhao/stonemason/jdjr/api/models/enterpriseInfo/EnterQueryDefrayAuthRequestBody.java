package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterQueryDefrayAuthRequestBody extends CommonRequest<EnterQueryDefrayAuthRequestBody> {
    private String merCustomerId;//30

    public EnterQueryDefrayAuthRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
