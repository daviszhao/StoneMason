package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterQueryInfoRequestBody extends CommonRequest<EnterQueryInfoRequestBody> {
    private String merCustomerId;//30

    public EnterQueryInfoRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
